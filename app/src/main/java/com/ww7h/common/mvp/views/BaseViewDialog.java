package com.ww7h.common.mvp.views;

import android.content.Context;

import com.ww7h.ww.common.dialogs.BaseDialog;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvp
 * 包名：     com.ww7h.common.mvp.views
 * 创建时间：  2019/5/9 21:06
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class BaseViewDialog extends BaseDialog {

    protected BaseViewDialog(Context context) {
        super(context);
    }

    protected BaseViewDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected BaseViewDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int getResourceId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAction() {

    }
}
