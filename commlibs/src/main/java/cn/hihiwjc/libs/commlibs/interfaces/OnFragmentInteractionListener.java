package cn.hihiwjc.libs.commlibs.interfaces;

import android.os.Bundle;

/**
 * 此接口为Fragment内部事件回调接口，当Fragment内部有事件需要让Activity知道时，就可让Activity实现这个接口
 * <p>Created by hihiwjc on 2015/9/14 0014.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public interface OnFragmentInteractionListener {
    /**
     * Fragment公用事件回调方法
     *
     * @param data 数据集合
     */
    void onFragmentInteraction(Bundle data);
}
