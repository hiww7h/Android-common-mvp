package com.ww7h.common.mvp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ww7h.common.mvp.contracts.MvpContract;
import com.ww7h.ww.common.bases.fragment.BaseFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvp
 * 包名：     com.ww7h.common.mvp.views
 * 创建时间：  2019/5/6 11:49
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public abstract class BaseViewFragment<P extends MvpContract.IPresenter, T extends BaseFragment<T>>
        extends BaseFragment<T> {

    protected P presenter;

    @Override
    protected boolean getDesignPattern() {
        return true;
    }

    @Nullable
    @Override
    protected View getContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container) {

        presenter = getPresenter();
        return inflater.inflate(getResourceId(), container, false);
    }

    /**
     * 获取当前视图需要用到的的Presenter
     * @return presenter
     */
    protected abstract P getPresenter();


    public void runOnUiThread(Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

}
