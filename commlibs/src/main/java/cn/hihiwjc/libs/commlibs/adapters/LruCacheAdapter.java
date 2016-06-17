package cn.hihiwjc.libs.commlibs.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

/**
 * 带图片缓存功能的试图适配器
 * 1.初始化图片缓存器(mBitmapCache)，用于缓存下载好的图片，LruCache类会在其中的内存达到设定值时将最近最少使用的图片对象移除
 * 2.监听AdapterView的滑动事件oScrollStateChanged(),在onScroll()中获得可见的条目数量(mVisibleItemCount)和第一个可见的条目编号(mFirstVisibleItemIndex)，
 * 只在滑动停止时联网加载图片loadBitmaps()，否则取消所有正在加载的图片下载任务cancelAll()
 * http://blog.csdn.net/guolin_blog/article/details/9526203
 * Created by hihiwjc on 2015/8/29 0029.
 * Author:hihiwjc
 * Email:hihiwjc@live.com
 */
public class LruCacheAdapter extends BaseAdapter implements OnScrollListener {
    /**
     * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
     */
    private LruCache<String, Bitmap> mBitmapCache;
    private List<Object> mDatas;
    private AdapterView mAdapterView;
    private Context mContext;
    /**
     * 记录所有正在下载或等待下载的任务。
     */
    private HashSet<BitmapWorkerTask> taskCollection = new HashSet<BitmapWorkerTask>();
    private boolean isFirstIn = true;
    /**
     * 第一张可见图片的下标
     */
    private int mFirstVisibleItemIndex;

    /**
     * 一屏有多少张图片可见
     */
    private int mVisibleItemCount;

    /**
     * 构建一个带图片缓存功能的试图适配器
     *
     * @param context     上下文
     * @param datas       数据
     * @param adapterView 被适配的视图
     */
    public LruCacheAdapter(Context context, List<Object> datas, AdapterView adapterView) {
        mContext = context;
        mDatas = datas;
        mAdapterView = adapterView;
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // 设置图片缓存大小为程序最大可用内存的1/8
        int cacheSize = maxMemory / 8;
        //mAdapterView滚动事件监听
        // TODO:mAdapterView滚动事件监听
        //mAdapterView.setOnScrollListener(this);
        mBitmapCache = new LruCache<String, Bitmap>(cacheSize) {
            /**重写sizeOf方法自定义内存大小计算方法
             * @param key 键
             * @param bitmap 位图
             * @return 位图的字节数
             */
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null) {
            return mDatas.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String url = (String) getItem(position);
        View view = null;
        if (convertView == null) {
            //todo 加载view
            // view = LayoutInflater.from(getContext()).inflate(R.layout.photo_layout, null);
        } else {
            view = convertView;
        }
        //todo 找到ImageVeiw
        /*
        final ImageView photo = (ImageView) view.findViewById(R.id.photo);*/
        //todo 给ImageView设置一个Tag，保证异步加载图片时不会乱序
        /*photo.setTag(url);
        setImageView(url, photo);*/
        return view;
    }

    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key    LruCache的键，这里传入图片的URL地址。
     * @param bitmap LruCache的键，这里传入从网络上下载的Bitmap对象。
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mBitmapCache.put(key, bitmap);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的Bitmap对象，或者null。
     */
    public Bitmap getBitmapFromMemoryCache(String key) {
        return mBitmapCache.get(key);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 仅当GridView静止时才去下载图片，GridView滑动时取消所有正在下载的任务
        if (scrollState == SCROLL_STATE_IDLE) {
            loadBitmaps(mFirstVisibleItemIndex, mVisibleItemCount);
        } else {
            cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        mFirstVisibleItemIndex = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        // 下载的任务应该由onScrollStateChanged里调用，但首次进入程序时onScrollStateChanged并不会调用，
        // 因此在这里为首次进入程序开启下载任务。
        if (isFirstIn && visibleItemCount > 0) {
            loadBitmaps(firstVisibleItem, visibleItemCount);
            isFirstIn = false;
        }
    }

    /**
     * 加载Bitmap对象。此方法会在LruCache中检查所有屏幕中可见的ImageView的Bitmap对象，
     * 如果发现任何一个ImageView的Bitmap对象不在缓存中，就会开启异步线程去下载图片。
     *
     * @param firstVisibleItem 第一个可见的ImageView的下标
     * @param visibleItemCount 屏幕中总共可见的元素数
     */
    private void loadBitmaps(int firstVisibleItem, int visibleItemCount) {
        try {
            for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                //todo 获取图片的下载地址
                //String imageUrl = Images.imageThumbUrls[i];
                String imageUrl = "";
                Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
                if (bitmap == null) {
                    BitmapWorkerTask task = new BitmapWorkerTask();
                    taskCollection.add(task);
                    task.execute(imageUrl);
                } else {
                    // todo设置并显示图片
                    /*ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
                    if (imageView != null && bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     */
    public void cancelAllTasks() {
        if (taskCollection != null) {
            for (BitmapWorkerTask task : taskCollection) {
                task.cancel(false);
            }
        }
    }

    /**
     * 异步下载图片的任务。
     *
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        /**
         * 图片的URL地址
         */
        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(params[0]);
            if (bitmap != null) {
                // 图片下载完成后缓存到LrcCache中
                addBitmapToMemoryCache(params[0], bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // todo 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
           /* ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }*/
            taskCollection.remove(this);
        }

        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl 图片的URL地址
         * @return 解析后的Bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }
}
