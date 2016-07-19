package cn.hihiwjc.libs.commlibs.views.autoscrollpager;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/5/31 0031
 * <br/>Func:
 */

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * CustomDurationScroller
 */
public class CustomDurationScroller extends Scroller {

    private double scrollFactor = 1;
    private int mDuration= 1600;;

    public CustomDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    /**
     * Set the factor by which the mDuration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        this.scrollFactor = scrollFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.mDuration/*(int)(mDuration * scrollFactor)*/);
    }
}