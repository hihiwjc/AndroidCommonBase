package com.hihiwjc.libs.common_libs.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hihiwjc.libs.common_libs.interfaces.OnFragmentInteractionListener;


/**
 * 这个是一个基本Fragment
 * <p>Created by hihiwjc on 2015/9/14 0014.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    /**
     * 视图id字符串，用于OnFragmentInteractionListener中获取视图的ID
     */
    public static final String KEY_VIEW_ID = "view_id";
    protected OnFragmentInteractionListener mListener;
    protected View mRootView;

    public BaseFragment() {
    }

    /**
     * 初始化Fragment要显示的视图
     *
     * @return Fragment要显示的视图
     */
    protected abstract View initFragmentViews();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = initFragmentViews();
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_VIEW_ID, v.getId());
        mListener.onFragmentInteraction(bundle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " 必须实现OnFragmentInteractionListener接口！");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mRootView = null;
    }

}
