package com.ww7h.common.mvp.presenters;

import com.ww7h.common.mvp.interfaces.MvpContract;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvp
 * 包名：     com.ww7h.common.mvp.presenters
 * 创建时间：  2019/5/5 20:32
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public abstract class BasePresenter<M extends MvpContract.ModelInterface,
        V extends MvpContract.ViewInterface<M>> {

    protected V viewInterface;


    public BasePresenter (V v) {
        viewInterface = v;
    }

}
