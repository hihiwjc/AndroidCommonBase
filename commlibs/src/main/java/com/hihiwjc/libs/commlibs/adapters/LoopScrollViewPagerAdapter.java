package com.hihiwjc.libs.commlibs.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/6/15 0015
 * <br/>Func:可循环滚动的ViewPager适配器
 */

public abstract class LoopScrollViewPagerAdapter extends PagerAdapter {
    public static final int FAKE_LIST_SIZE = 100000;//假的ViewPager条目数量
    private List<Object> mItems = null;

    public LoopScrollViewPagerAdapter(List<Object> items) {
        mItems = items;
    }

    @Override
    public int getCount() {
        return FAKE_LIST_SIZE;
    }

    /**
     * position %= mItems.size();//计算真实位置<br/>
     * View itemView=LayoutInflater.from(context).inflate(R.layout.xxx,container,false);<br/>
     * //其它操作<br/>
     * container.addView(itemView);<br/>
     */
    @Override
    public abstract Object instantiateItem(ViewGroup container, int position);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
