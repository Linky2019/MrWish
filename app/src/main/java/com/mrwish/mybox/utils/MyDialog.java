package com.mrwish.mybox.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.baselibrary.utils.AbToastUtil;
import com.mrwish.mybox.R;


public class MyDialog extends Dialog {

    /**
     * MyDialog
     */
    private static MyDialog myDialog;
    /**
     * canNotCancel, the mDialogTextView dimiss or undimiss flag
     */
    private boolean canNotCancel;
    /**
     * if the mDialogTextView don't dimiss, what is the tips.
     */
    private String tipMsg;

    private TextView mShowMessage;
    private Context context;
    /**
     * the MyDialog constructor
     *
     * @param ctx          Context
     * @param canNotCancel boolean
     * @param tipMsg       String
     */
    public MyDialog(final Context ctx, boolean canNotCancel, String tipMsg) {
        super(ctx);
        context = ctx;
        this.canNotCancel = canNotCancel;
        this.tipMsg = tipMsg;
        this.getContext().setTheme(R.style.myLoading);
        setContentView(R.layout.layout_dialog_loading);

        if (!TextUtils.isEmpty(this.tipMsg)) {
            mShowMessage = (TextView) findViewById(R.id.show_message);
            mShowMessage.setText(this.tipMsg);
        }

        Window window = getWindow();
        WindowManager.LayoutParams attributesParams = window.getAttributes();
        attributesParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.5f;
        window.setAttributes(attributesParams);
        setCanceledOnTouchOutside(false);//点外面禁止关闭
        window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        getWindow().setDimAmount(0f);//核心代码 解决了无法去除遮罩问题
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);//去除蒙层
        setCancelable(false);//禁止物理返回键关闭
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (canNotCancel) {
                AbToastUtil.showToast(context,tipMsg);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * show the mDialogTextView
     *
     * @param context
     */
    public static void show(Context context) {
        show(context, null, false);
    }

    /**
     * show the mDialogTextView
     *
     * @param context Context
     * @param message String
     */
    public static void show(Context context, String message) {
        show(context, message, false);
    }

    /**
     * show the mDialogTextView
     *
     * @param context  Context
     * @param message  String, show the message to user when isCancel is true.
     * @param isCancel boolean, true is can't dimiss，false is can dimiss
     */
    private static void show(Context context, String message, boolean isCancel) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (myDialog != null && myDialog.isShowing()) {
            return;
        }
        myDialog = new MyDialog(context, isCancel, message);
        myDialog.show();
    }

    /**
     * dismiss the mDialogTextView
     */
    public static void dismiss(Context context) {
        try {
            if (context instanceof Activity) {
                if (((Activity) context).isFinishing()) {
                    myDialog = null;
                    return;
                }
            }

            if (myDialog != null && myDialog.isShowing()) {
                Context loadContext = myDialog.getContext();
                if (loadContext != null && loadContext instanceof Activity) {
                    if (((Activity) loadContext).isFinishing()) {
                        myDialog = null;
                        return;
                    }
                }
                myDialog.dismiss();
                myDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            myDialog = null;
        }
    }
}
