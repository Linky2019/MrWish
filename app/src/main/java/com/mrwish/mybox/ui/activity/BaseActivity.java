package com.mrwish.mybox.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mrwish.mybox.R;
import com.mrwish.mybox.utils.App;
import com.mrwish.mybox.utils.MyDialog;


public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    private ViewFlipper mContentView;
    protected RelativeLayout mHeadLayout;
    private LinearLayout lay_left,lay_right;
    protected TextView mTitle,tv_right;
    private Drawable mBtnBackDrawable;

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 禁用横屏

        try {
            //去除灰色遮罩
            //Android5.0以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){//Android4.4以上,5.0以下
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            //设置导航栏字体为黑色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }catch (Exception e){

        }

        mContext = this;
        App.getInstance().addActivity(this);//加入队列 退出app时全部销毁activity
//        setSystemBar();
//        handleMaterialStatusBar();

        // 初始化公共头部
        mContentView = (ViewFlipper) super.findViewById(R.id.layout_container);
        mHeadLayout = (RelativeLayout) super.findViewById(R.id.layout_head);
        lay_left =  super.findViewById(R.id.lay_left);
        lay_right = super.findViewById(R.id.lay_right);
        mTitle = (TextView) super.findViewById(R.id.tv_title);
        tv_right = (TextView) super.findViewById(R.id.tv_right);
        mBtnBackDrawable = getResources().getDrawable(R.mipmap.top_back);
        mBtnBackDrawable.setBounds(0, 0, mBtnBackDrawable.getMinimumWidth(),
                mBtnBackDrawable.getMinimumHeight());
        lay_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeadRightButtonClick(v);
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeadRightTextClick(v);
            }
        });
        lay_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mContentView.addView(view, lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }


    /**
     * 设置头部是否可见
     *
     * @param visibility
     */
    public void setHeadVisibility(boolean visibility) {

        if (!visibility){
            mHeadLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左边是否可见
     *
     * @param visibility
     */
    public void setHeadLeftButtonVisibility(int visibility) {
//        mBtnLeft.setVisibility(visibility);
        lay_left.setVisibility(visibility);
    }

    /**
     * 设置右边是否可见
     *
     * @param visibility
     */
    public void setHeadRightButtonVisibility(int visibility) {
        lay_right.setVisibility(visibility);
    }

    /**
     * 设置右边文字按钮是否可见
     *
     * @param visibility
     */
    public void setHeadRightTextVisibility(int visibility) {
        tv_right.setVisibility(visibility);
    }
    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        setTitle(getString(titleId), false);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId, boolean flag) {
        setTitle(getString(titleId), flag);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        setTitle(title, false);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, boolean flag) {
        mTitle.setText(title);
//        if (flag) {
//            mBtnLeft.setCompoundDrawables(null, null, null, null);
//        } else {
//            mBtnLeft.setCompoundDrawables(mBtnBackDrawable, null, null, null);
//        }
    }

    /**
     * 设置右边按钮文字
     *
     * @param title
     */
    public void setRightText(String title) {
        tv_right.setText(title);
    }

    /**
     * 点击左按钮
     */
    public void onHeadLeftButtonClick(View v) {
        finish();
    }

    /**
     * 点击右按钮
     */
    public void onHeadRightButtonClick(View v) {

    }

    /**
     * 点击右发布按钮
     */
    public void onHeadRightTextClick(View v) {

    }
    public Drawable getHeadBackButtonDrawable() {
        return mBtnBackDrawable;
    }

    public void setBackButtonDrawable(Drawable backButtonDrawable) {
        this.mBtnBackDrawable = backButtonDrawable;
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    protected void showLoadDialog() {
        try {
            MyDialog.show(mContext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void dismissLoadDialog() {
        try {
            MyDialog.dismiss(mContext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);

    }

    @Override
    public Resources getResources() {
        //字体大小不受系统字体大小改变的影响
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
}
