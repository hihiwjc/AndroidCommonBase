package com.hihiwjc.libs.commlibs.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 基本Activity</b>
 * <p>Created by hihiwjc on 2015/9/14 0014.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * Activity根视图
     */
    protected View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityViews();
    }

    /**
     * 初始化Activity视图，可以在这里实例化布局，此时还未调用setContentView(int resID)方法
     */
    protected void initActivityViews() {
        throw new UnsupportedOperationException("这个方法必须被重写，用于初始化Activity的视图");
    }
}
