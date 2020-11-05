package com.mrwish.mybox.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.baselibrary.utils.AbToastUtil;
import com.mrwish.mybox.R;


/**
 * 启动页*/
public class SplashActivity extends BaseActivity {

    private ImageView iv_adlogo;
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setHeadVisibility(false);

        initView();
        initData();

    }

    void initView() {
        iv_adlogo = findViewById(R.id.iv_adlogo);
    }
    void initData() {
        settings = getSharedPreferences("first_open_xy", 0);
        Boolean user_first = settings.getBoolean("FIRST", true);
        if (user_first) {// 第一次则跳转到同意协议页面
            MyDialog dialog = new MyDialog(this);
            dialog.show();
        } else {
            handActivity();
//            checkReadPermission();
        }


    }
    private void handActivity() {
//        if (StoredData.getThis().isFirstOpen()) {
//            Intent intent = new Intent(this, WellcomeActivity.class);
//            startActivity(intent);
//            StoredData.getThis().two();
//            finish();
//            return;
//        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        }, 2000);
    }

    private void goToMain() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //自定义对话框
    class MyDialog extends Dialog implements View.OnClickListener {
        private TextView tvContent, txt_title;
        private Button btnCancel, btnConfirm;


        public MyDialog(Context context) {
            super(context, R.style.dialog);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_xieyi);
            setCanceledOnTouchOutside(false);//点外面禁止关闭
            setCancelable(false);//禁止物理返回键关闭弹窗
            getWindow().setWindowAnimations(R.style.dialog_animation); //设置窗口弹出动画

            tvContent = ((TextView) findViewById(R.id.txt_content));
            //将TextView的显示文字设置为SpannableString
            tvContent.setText(getClickableSpan());
            //设置该句使文本的超连接起作用
            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
            txt_title = ((TextView) findViewById(R.id.txt_title));
            btnCancel = ((Button) findViewById(R.id.btn_cancel));
            btnConfirm = ((Button) findViewById(R.id.btn_confirm));
            btnCancel.setOnClickListener(this);
            btnConfirm.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_confirm:
                    settings.edit().putBoolean("FIRST", false).commit();
                    dismiss();

//                    checkReadPermission();
                    goToMain();
                    break;

                case R.id.btn_cancel:
                   finish();
                    dismiss();
                    break;
            }
        }


    }

    //设置超链接文字
    private SpannableString getClickableSpan() {
        SpannableString spanStr = new SpannableString("为保障您的权益，请您务必审慎阅读、充分理解《服务协议》和《隐私政策》的条款详细内容，以了解您的权利和义务。如您同意，请点击“同意”即表示您已充分阅读并接受以上协议内容。");
        //设置下划线文字
        spanStr.setSpan(new UnderlineSpan(), 21, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spanStr.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
//                WebViewActivity.toWebView(mContext, Constants.URL_FW,"服务协议");
            }
        }, 21, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 21, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置下划线文字
        spanStr.setSpan(new UnderlineSpan(), 28, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spanStr.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
//                WebViewActivity.toWebView(mContext,  Constants.URL_YS,"隐私政策");
            }
        }, 28, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 28, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }

    //存取 权限申请
    private final int PERMISSION_READ = 1;//读取权限
    private void checkReadPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_READ);
        }else {
            checkWritePermission();
        }
    }
    private final int PERMISSION_WRITE = 2;//写权限
    private void checkWritePermission() {//8.0以上系统要单独申请写权限
        if (Build.VERSION.SDK_INT >= 26 && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE);
        }else {
            handActivity();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case PERMISSION_READ:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkWritePermission();
                }else {
                    finish();
                    AbToastUtil.showToast(this, "取消授权无法进入");
                }
                break;
            case PERMISSION_WRITE:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        finish();
                        AbToastUtil.showToast(this, "取消授权无法进入应用");
                    }else {
                        handActivity();
                    }
                }
                break;
        }
    }
}
