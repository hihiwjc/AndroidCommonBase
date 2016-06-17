package cn.hihiwjc.libs.commlibs.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 这个是一个基本列表适配器
 * <p>Created by hihiwjc on 2015/9/14 0014.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public abstract class BaseListAdapter<D> extends BaseAdapter {
    private Context mContext;
    private List<D> mDatas;
    private int mLayoutID;

    public BaseListAdapter(Context context, List<D> datas, int layout_id) {
        mContext = context;
        mDatas = datas;
        mLayoutID = layout_id;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public D getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mLayoutID, parent, false);
        }
        CommonViewHolder viewHolder = CommonViewHolder.get(convertView);
        setViewData(viewHolder, convertView, getItem(position));
        return convertView;
    }

    /**
     * getView中间子View的内容设置的代码提出来，向外提供一个抽象发放，自定义实现。
     *
     * @param commonViewHolder 当前的ViewHolder
     * @param currentView      当前的View
     * @param item             对应View的数据
     */
    public abstract void setViewData(CommonViewHolder commonViewHolder, View currentView, D item);

    private static class CommonViewHolder extends SparseArray<View> {
        /**
         * @param view Adapter中传过来的View
         * @return 返回一个ViewHolder
         */
        public static CommonViewHolder get(View view) {
            CommonViewHolder viewHolder = (CommonViewHolder) view.getTag();
            if (viewHolder == null) {
                viewHolder = new CommonViewHolder();
                view.setTag(viewHolder);
            }
            return viewHolder;
        }

        /**
         * 此方法在CommonAdapter的子类中有使用，返回一个View,可以来设置View的内容
         *
         * @param commonViewHolder 当前View对应的ViewHolder
         * @param currentView      当前的View
         * @param key              View中的一个控件View存放的Key
         * @return 视图
         */
        public View get(CommonViewHolder commonViewHolder, View currentView, int key) {
            View view = get(key);
            if (view == null) {
                view = currentView.findViewById(key);
                commonViewHolder.put(key, view);
            }
            return view;
        }

    }
}
