package cn.hihiwjc.libs.commlibs.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/27 0027
 * <br/>Func:Common RecyclerView ViewHolder
 */

public class CommRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mItemView;

    public CommRecyclerViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViews=new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
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
}
