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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.baselibrary.utils.AbSharedUtil;
import com.baselibrary.utils.AbStrUtil;
import com.baselibrary.utils.AbToastUtil;
import com.mrwish.mybox.Api.Constants;
import com.mrwish.mybox.R;
import com.mrwish.mybox.presenters.LoginHelper;
import com.mrwish.mybox.presenters.viewinterface.LoginView;
import com.mrwish.mybox.utils.AbResult;

import org.json.JSONException;
import org.json.JSONObject;


/**
 *登录页*/
public class LoginActivity extends BaseActivity implements LoginView {

    private ImageView iv_adlogo;
    private EditText et_phone,et_code;
    private TextView tv_sand;
    private Button btn_ok;
    LoginHelper loginHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        initView();
        initData();

    }

    void initView() {
        iv_adlogo = findViewById(R.id.iv_adlogo);
        et_phone = findViewById(R.id.et_phone);
        et_code = findViewById(R.id.et_code);
        tv_sand = findViewById(R.id.tv_sand);
        btn_ok = findViewById(R.id.btn_ok);
    }
    void initData() {
        loginHelper = new LoginHelper(this);
    btn_ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (AbStrUtil.isEmpty(et_phone.getText().toString())){
                AbToastUtil.showToast(mContext,"请输入手机号码");
                return;
            }
            if (AbStrUtil.isEmpty(et_code.getText().toString())){
                AbToastUtil.showToast(mContext,"请输入验证码");
                return;
            }
            loginHelper.getLogin(Constants.BOX_TENANTID,et_phone.getText().toString(),et_code.getText().toString());
        }
    });
        tv_sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AbStrUtil.isEmpty(et_phone.getText().toString())){
                    AbToastUtil.showToast(mContext,"请输入手机号码");
                    return;
                }

            }
        });
    }

    @Override
    public void onLogin(String result) {
        if (result!=null) {
            try {
                JSONObject object = new JSONObject(result);
//                int code = object.optInt("code");
//                String msg = object.optString("message");
//                AbToastUtil.showToast(mContext,msg);
//                if (AbResult.RESULT_OK == code){
//                    String data = object.optString("data");
//                    JSONObject dataobj = new JSONObject(data);
//
//                }
                AbSharedUtil.putString(mContext, Constants.BOX_TOKEN, object.getString("token"));
                finish();
                AbToastUtil.showToast(mContext,"登录成功");
            } catch (JSONException e) {
                e.printStackTrace();
                AbToastUtil.showToast(mContext,"改手机号未绑定");
            }
        } else {
            AbToastUtil.showToast(mContext,mContext.getResources().getString(R.string.time_out));
        }
    }
}
