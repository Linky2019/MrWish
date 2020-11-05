/**
 * @Title: LoadingPage.java
 * @Package com.duowan.android.xianlu.common.page
 * @Description: 加载中页面
 * Copyright: Copyright (c) 2014
 * Company:YY Inc
 * @author 2yuan
 * @date Oct 20, 2015 11:14:50 AM
 * @version V1.0
 */

package com.baselibrary.widget;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.baselibrary.R;


/**
 * @ClassName: LoadingPage
 * @Description: 加载中页面
 *
 */

public class LoadingPage {

    private static final String tag = LoadingPage.class.getName();

    private Activity activity;
    private String noticeMsg; // 要显示的文本信息

    private LinearLayout pageLoadingLlRoot; // 加载中页面的LinearLayout 根
    private LinearLayout pageLoadingLl; // 加载中页面的LinearLayout
    private ImageView pageLoadingIv; // 加载中图片ImageView
    private TextView pageLoadingTv; // 提示文本信息 TextView

    private WindowManager wm;
    private AnimationDrawable animationDrawable;

    /**
     *
     * 创建一个新的实例 LoadingPage.
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param activity
     *            Activity
     * @param noticeMsg
     *            提示信息
     */
    public LoadingPage(Activity activity, String noticeMsg) {
        this.activity = activity;
        this.noticeMsg = noticeMsg;
        initView();
    }

    private void initView() {
        pageLoadingLlRoot = (LinearLayout) activity.findViewById(R.id.page_loading_ll_root);
        pageLoadingLl = (LinearLayout) activity.findViewById(R.id.page_loading_ll);
        pageLoadingIv = (ImageView) activity.findViewById(R.id.page_loading_iv);
        pageLoadingTv = (TextView) activity.findViewById(R.id.page_loading_tv);

        setLayout();
    }

    private void setLayout() {
        // pageLoadingLinearLayout.getBackground().setAlpha(210);
        wm = activity.getWindow().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.d(tag, "width=" + width + ", height=" + height);
        LayoutParams lp = new LayoutParams(width, height);
        pageLoadingLl.setLayoutParams(lp);

        pageLoadingIv.setImageResource(R.drawable.anim_ptr_footer);
        animationDrawable = (AnimationDrawable) pageLoadingIv.getDrawable();
        // animationDrawable.start();

        // pageLoadingIv.getViewTreeObserver().addOnPreDrawListener(new
        // OnPreDrawListener() {
        // @Override
        // public boolean onPreDraw() {
        // Log.d(tag, "setLayout onPreDraw begin animationDrawable.isRunning()="
        // + animationDrawable.isRunning());
        // if (!animationDrawable.isRunning()) {
        // animationDrawable.start();
        // }
        // Log.d(tag, "setLayout onPreDraw end animationDrawable.isRunning()=" +
        // animationDrawable.isRunning());
        // return true;
        // }
        // });

        if (noticeMsg ==null || noticeMsg.isEmpty()) {
            noticeMsg = "加载中";
        }
        pageLoadingTv.setText(noticeMsg);
    }

    /**
     * 显示加载页
     *
     * @Title: show
     * @Description: 显示加载页
     */
    public void show() {
        pageLoadingLlRoot.setVisibility(View.VISIBLE);
        pageLoadingLl.setVisibility(View.VISIBLE);

        Log.d(tag, "show begin animationDrawable.isRunning()=" + animationDrawable.isRunning());
        // pageLoadingIv.post(new Runnable() {
        // @Override
        // public void run() {
        // animationDrawable.start();
        // }
        // });

        // if(animationDrawable.isRunning()) {
        // animationDrawable.stop();
        // }
        if (!animationDrawable.isRunning()) {
            animationDrawable.stop();
            animationDrawable.start();
        }

        Log.d(tag, "show end animationDrawable.isRunning()=" + animationDrawable.isRunning());
    }

    /**
     * 显示加载页
     *
     * @Title: show
     * @Description: 显示加载页
     * @param needHideView
     *            需要隐藏的布局View
     */
    public void show(View needHideView) {
        if (needHideView != null) {
            needHideView.setVisibility(View.GONE);
        }

        show();
    }

    /**
     * 显示加载页
     *
     * @Title: show
     * @Description: 显示加载页
     * @param needHideViewArr
     *            View[] 需要隐藏的布局View数组
     */
    public void show(View[] needHideViewArr) {
        if (needHideViewArr != null && needHideViewArr.length > 0) {
            for (int i = 0; i < needHideViewArr.length; i++) {
                View needHideView = needHideViewArr[i];
                if (needHideView != null && needHideView.getVisibility() == View.VISIBLE) {
                    needHideView.setVisibility(View.GONE);
                }
            }
        }

        show();
    }

    /**
     * 隐藏加载页
     *
     * @Title: hide
     * @Description: 隐藏加载页
     */
    public void hide() {
        pageLoadingLl.setVisibility(View.GONE);
        pageLoadingLlRoot.setVisibility(View.GONE);
        Log.d(tag, "hide begin animationDrawable.isRunning()=" + animationDrawable.isRunning());
        animationDrawable.stop();
        Log.d(tag, "hide begin animationDrawable.isRunning()=" + animationDrawable.isRunning());
    }

    /**
     * 隐藏加载页
     *
     * @Title: hide
     * @Description: 隐藏加载页
     * @param needShowView
     *            需要显示的布局View
     */
    public void hide(View needShowView) {
        if (needShowView != null) {
            needShowView.setVisibility(View.VISIBLE);
        }
        hide();
    }

    /**
     * 隐藏加载页
     *
     * @Title: hide
     * @Description: 隐藏加载页
     * @param needShowViewArr
     *            View[] 需要显示的布局View数组
     */
    public void hide(View[] needShowViewArr) {
        if (needShowViewArr != null && needShowViewArr.length > 0) {
            for (int i = 0; i < needShowViewArr.length; i++) {
                View needShowView = needShowViewArr[i];
                if (needShowView != null) {
                    needShowView.setVisibility(View.VISIBLE);
                }
            }
        }
        hide();
    }

}
