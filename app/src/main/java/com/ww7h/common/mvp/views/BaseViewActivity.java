package com.ww7h.common.mvp.views;

import com.ww7h.common.mvp.interfaces.MvpInterface;
import com.ww7h.common.mvp.presenters.BasePresenter;
import com.ww7h.ww.common.bases.activity.BaseActivity;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvp
 * 包名：     com.ww7h.common.mvp.presenters
 * 创建时间：  2019/5/5 20:21
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public abstract class BaseViewActivity<P> extends BaseActivity<BaseViewActivity<P>> {

    protected P[] presenters;

    @Override
    protected boolean getDesignPattern() {
        return true;
    }

    @Override
    protected void initContentView() {
        super.initContentView();
        setContentView(getContentView());

        presenters = getPresenters();

    }

    /**
     * 获取当前视图需要用到的所有的Presenter
     * @return presenters
     */
    protected abstract P[] getPresenters();

}
