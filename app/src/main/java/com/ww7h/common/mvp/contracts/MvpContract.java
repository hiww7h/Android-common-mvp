package com.ww7h.common.mvp.contracts;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvp
 * 包名：     com.ww7h.common.mvp.interfaces
 * 创建时间：  2019/5/5 20:24
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public interface MvpContract {

    interface IModel {

    }

    interface IView {

        void runOnUiThread(Runnable runnable);

    }

    interface IPresenter {

        void runOnWorkerThread(Runnable runnable);

    }

}
