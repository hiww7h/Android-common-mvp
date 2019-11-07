# 概述

## MVP简介（VIew-Model-Presenter）

![Image text](http://upload-images.jianshu.io/upload_images/2419777-ed77816002cb05b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
Presenter整个功能的操作，当用户通过界面（View）与应用进行交互或潜在交互时，界面将有效事件上报给Presenter，Presenter则根据事件需求进行处理，包括与数据（Model）之间的交互。形象来说就是：MVP就是一个非自助式餐厅，顾客就相当于我们的用户，餐厅本身相当于View，餐厅工作人员相当于Presenter，餐厅的食材相当于Model。用户与View进行交互时，相当于客户来到餐厅，此时会触发工作人员进行服务，有点餐操作时，Presenter获取Model数据，并呈现给用户。

## MVP 优缺点

### 优点

降低代码的耦合度，便于团队协同开发，提高代码复用，简化代码逻辑结构

### 缺点

对开发人员代码编写有了更高的要求，各个模块的编写要更多的考虑重用性和耦合度，接口文件的加入增加了整体的代码量。在部分简单的界面编写时肯能会出现MVP逻辑比非MVP更复杂。

## MVP 的准备工作

### 各模块的划分

M：根据项目复杂程度可直接使用JavaBean，亦或包含数据处理模块的JavaBeanManager
V：一般使用activity、fragment、dialog、popupwindow，亦可是自定义Layout，一般一个V至少对应一个P
P：单独定义的模块，完成界面和数据的交互

### 使用工厂模式 + 代理模式 实现框架封装

#### BaseView的定义

1、BaseViewActivity

    package com.ww7h.common.mvp.views;

    import com.ww7h.common.mvp.contracts.MvpContract;
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
    public abstract class BaseViewActivity<P extends MvpContract.IPresenter>
            extends BaseActivity<BaseViewActivity<P>> {

        protected P iPresenter;

        @Override
        protected boolean getDesignPattern() {
            return true;
        }

        @Override
        protected void initContentView() {
            super.initContentView();
            setContentView(getContentView());

            iPresenter = getPresenter();

        }

        /**
        * 获取当前视图需要用到的的Presenter
        * @return presenter
        */
        protected abstract P getPresenter();

    }

2、BaseViewFragment

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
    public abstract class BaseViewFragment<P extends MvpContract.IPresenter>
            extends BaseFragment<BaseViewFragment<P>> {

        protected P presenter;

        @Override
        protected boolean getDesignPattern() {
            return true;
        }

        @Nullable
        @Override
        protected View getContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container) {

            presenter = getPresenter();
            return inflater.inflate(getResourceId(), container);
        }

        /**
        * 获取当前视图需要用到的的Presenter
        * @return presenter
        */
        protected abstract P getPresenter();

    }

#### BaseModel的定义

    package com.ww7h.common.mvp.models;

    /**
    * ================================================
    * 描述：
    * 来源：     Android Studio.
    * 项目名：   Android-common-mvp
    * 包名：     com.ww7h.common.mvp.models
    * 创建时间：  2019/5/5 20:32
    *
    * @author ww  Github地址：https://github.com/ww7hcom
    * ================================================
    */
    public class BaseModel {

        protected final String TAG = BaseModel.class.getName();


    }

#### BasePresenter定义

    package com.ww7h.common.mvp.presenters;

    import com.ww7h.common.mvp.contracts.MvpContract;

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
    public abstract class BasePresenter<M extends MvpContract.IModel,
            V extends MvpContract.IView> {

        protected V iView;
        protected M iModel;

        public BasePresenter (V v) {
            iView = v;
            iModel = createModel();
            iModels = createModels();
        }

        /**
        * 创建数据模型
        * @return 返回数据模型
        */
        protected abstract M createModel();

    }

#### MvpContract定义（各个模块在被调用时提供的接口）

    package com.ww7h.common.mvp.contracts;

    /**
    * ================================================
    * 描述：
    * 来源：     Android Studio.
    * 项目名：   Android-common-mvp
    * 包名：     com.ww7h.common.mvp.contracts
    * 创建时间：  2019/5/5 20:24
    *
    * @author ww  Github地址：https://github.com/ww7hcom
    * ================================================
    */
    public interface MvpContract {

        public interface IModel {

        }

        public interface IView {



        }

        public interface IPresenter {


        }

    }

## MVP的使用

### Activity 定义

    package com.ww7h.mvp;

    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import com.ww7h.common.mvp.views.BaseViewActivity;

    /**
    * @author ww
    */
    public class MainActivity extends BaseViewActivity<MainContract.MainIPresenter>
            implements MainContract.MainIView {

        private TextView resultShowTv;
        private Button plusOneBin;

        @Override
        protected MainContract.MainIPresenter getPresenter() {
            return new MainPresenter(this);
        }

        @Override
        protected int getContentView() {
            return R.layout.activity_main;
        }

        @Override
        protected void initAction() {
            plusOneBin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iPresenter.requestData();
                }
            });
        }

        @Override
        protected void initView() {
            resultShowTv = findViewById(R.id.result_show_tv);
            plusOneBin = findViewById(R.id.plus_one_bin);
        }


        @Override
        public void resultData(MainContract.MainIModel mainModel) {
            resultShowTv.setText(mainModel.getData());
        }
    }

### 布局文件

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".MainActivity">

        <TextView
            android:id="@+id/result_show_tv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Hello World!" />

        <Button
            android:id="@+id/plus_one_bin"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>

### Presenter定义

    package com.ww7h.mvp;

    import com.ww7h.common.mvp.presenters.BasePresenter;

    /**
    * ================================================
    * 描述：
    * 来源：     Android Studio.
    * 项目名：   MvpTest
    * 包名：     com.ww7h.mvp
    * 创建时间：  2019/5/6 11:02
    *
    * @author ww  Github地址：https://github.com/ww7hcom
    * ================================================
    */
    public class MainPresenter extends BasePresenter<MainContract.MainIModel,
            MainContract.MainIView> implements MainContract.MainIPresenter {

        public MainPresenter(MainContract.MainIView mainViewInterface) {
            super(mainViewInterface);
        }

        @Override
        protected MainContract.MainIModel createModel() {
            return new MainModel();
        }

        @Override
        public void requestData() {


            iView.resultData(iModel);
        }
    }

### Contract定义

    package com.ww7h.mvp;

    import com.ww7h.common.mvp.contracts.MvpContract;

    /**
    * ================================================
    * 描述：
    * 来源：     Android Studio.
    * 项目名：   MvpTest
    * 包名：     com.ww7h.mvp
    * 创建时间：  2019/5/6 11:03
    *
    * @author ww  Github地址：https://github.com/ww7hcom
    * ================================================
    */
    public interface MainContract {

        interface MainIModel extends MvpContract.IModel {

            String getData();

        }

        interface MainIView extends MvpContract.IView {

            void resultData(MainIModel mainModel);

        }

        interface MainIPresenter extends MvpContract.IPresenter {

            void requestData();

        }

    }

### 运行

    效果很low，就不展示了，后续会补充各种情况，并根据需求完善MVP库。

## 写在后面的话

1、上述用到基础框架

Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

Step 2. Add the dependency

    dependencies {
        implementation 'com.github.ww7hcom:Android-common:1.0.25'
    }

2、MVP 库使用

Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }Copy

Step 2. Add the dependency

    dependencies {
            implementation 'com.github.ww7hcom:Android-common-mvp:1.0.3'
    }

3、当有一个View需要对应多个Presenter时，使用当前View特有的Presenter调用其他Presenter

4、当有一个View需要使用多个Model，并且当前View仅对应一个Presenter时，使用ModelManager来统筹这些Model，整体逻辑：

    一个View 只能关联一个 Presenter；
    一个Presenter只能关联一个Model或者一个ModelManager。

    但是，
    一个Presenter可以操作多个Presenter，也可以被多个View关联
    一个ModelManager可以管理多个Model，同时一个ModelManager或Model可以被多个resenter关联

❆ 上述是必须吗？否！！！只是相关建议！！！不喜勿喷，欢迎交流！！！！


前期代码量较少，但也已上传GitHub，后续会不断更新、扩充。


[🔗MVP库代码链接](https://github.com/ww7hcom/Android-common-mvp)

[🔗基础库代码链接](https://github.com/ww7hcom/Android-common)