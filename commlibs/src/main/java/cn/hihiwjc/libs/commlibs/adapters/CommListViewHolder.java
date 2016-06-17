package cn.hihiwjc.libs.commlibs.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/27 0027
 * <br/>Func:Common ListView ViewHolder
 */

public class CommListViewHolder {
    public  int position;
    public View mConvertView;
    public ViewGroup mParentView;
    public Context mContext;
    private SparseArray<View> mViews;

    public CommListViewHolder(Context mContext, ViewGroup mParentView, int layoutId, int position) {
        this.mContext = mContext;
        this.mParentView = mParentView;
        this.position = position;
        this.mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(mContext).inflate(layoutId, mParentView, false);
        this.mConvertView.setTag(this);
    }

    public static CommListViewHolder getHolder(Context mContext, ViewGroup mParentView, View convertView, int layoutId, int position) {
        if (convertView == null)
            return new CommListViewHolder(mContext, mParentView, layoutId, position);
        else {
            CommListViewHolder holder = (CommListViewHolder) convertView.getTag();
            holder.position = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.append(viewId, view);
        }
        return (T) view;
    }

    public void setViewImage(int layoutId, int resId) {
        ImageView imageView = getView(layoutId);
        if (imageView != null)
            imageView.setImageResource(resId);
    }

    public void setViewImage(int layoutId, Drawable drawable) {
        ImageView imageView = getView(layoutId);
        if (imageView != null)
            imageView.setImageDrawable(drawable);
    }

    public void setViewText(int layoutId, int resId) {
        TextView textView = getView(layoutId);
        if (textView != null)
            textView.setText(resId);
    }

    public void setViewText(int layoutId, String text) {
        TextView textView = getView(layoutId);
        if (textView != null)
            textView.setText(text);
    }

    public void setViewTag(int layoutId, Object viewTag) {
        View view = getView(layoutId);
        if (view != null)
            view.setTag(viewTag);
    }

    public void setViewKeyedTag(int layoutId, int key, Object viewTag) {
        View view = getView(layoutId);
        if (view != null)
            view.setTag(key, viewTag);
    }

/*    public void showNetImage(int layoutId, String url) {
        ImageView imageView = getView(layoutId);
        if (imageView != null && !url.equals(imageView.getTag()))
            ImageLoader.getInstance().displayImage(url, imageView, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    view.setTag(imageUri);
                }
            });
    }*/


    //可以继续封装需要的方法...
}
