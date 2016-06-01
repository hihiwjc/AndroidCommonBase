package com.hihiwjc.libs.commlibs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/27 0027
 * <br/>Func:Base RecyclerView Adapter
 */

public abstract class BaseRecyclerAdapter<M extends Object> extends RecyclerView.Adapter<CommRecyclerViewHolder> {
    protected List<M> mData;
    private Context mContext;
    private RecyclerView mParentView;

    public BaseRecyclerAdapter(Context context, RecyclerView parentView) {
        mContext = context;
        mParentView = parentView;
        if (mParentView == null) {
            throw new NullPointerException("parentView Can not be null!");
        }
    }

    @Override
    public CommRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRootView = LayoutInflater.from(mContext).inflate(getItemLayoutID(), mParentView, false);
        return new CommRecyclerViewHolder(itemRootView);
    }

    /**
     * 填充数据
     */
    public void fillData(List<M> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * ItemView的视图ID
     */
    protected abstract int getItemLayoutID();

    /**
     * 填充Item视图数据
     */
    protected abstract void fillItemViewData(M mod, CommRecyclerViewHolder holder, int position);

    @Override
    public void onBindViewHolder(CommRecyclerViewHolder holder, int position) {
        if (mData == null) {
            return;
        }
        M mod = mData.get(position);
        fillItemViewData(mod, holder, position);
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
