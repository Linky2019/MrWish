package com.mrwish.mybox.presenters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 页面展示逻辑基类
 */
public abstract class Presenter {

    public static ExecutorService SINGLE_TASK_EXECUTOR;
    public static ExecutorService LIMITED_TASK_EXECUTOR;
    public static ExecutorService FULL_TASK_EXECUTOR;

    static {
        SINGLE_TASK_EXECUTOR = Executors.newSingleThreadExecutor();
        LIMITED_TASK_EXECUTOR = Executors.newFixedThreadPool(15);
        FULL_TASK_EXECUTOR = Executors.newCachedThreadPool();
    };

    //销去持有外部的mContext;
    public abstract void onDestory();
}
