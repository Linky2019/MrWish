

package com.baselibrary.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselibrary.R;
import com.baselibrary.utils.AbStrUtil;
import com.baselibrary.utils.MapUtil;

import java.util.Map;

/**
 * @ClassName: ErrorNetworkPage
 * @Description: 网络连接失败页面
 * @author 2yuan
 * @date Oct 14, 2015 3:33:03 PM
 *
 */

public class ErrorNetworkPage implements OnClickListener {

    private static final String tag = ErrorNetworkPage.class.getName();

    private Activity activity;
    private Map<String, Object> paramMap;

    private RelativeLayout fErrorNetworkLl; // 消息空白 LinearLayout
    private ImageView fErrorNetworkIvImage; // 显示的图片
    private TextView fErrorNetworkTvComment; // 提示文本信息 TextView
    private Button fErrorNetworkBtn; // 重新加载Button

    public static final class PARAM_MAP_KEY {
        public static final String NOTICE_MSG = "NOTICE_MSG"; // 提示消息
        public static final String IS_HIDE_IMAGE = "IS_HIDE_IMAGE"; // 是否隐藏图片
        public static final String IMAGE_RES_ID = "IMAGE_RES_ID"; // 图片ID
        public static final String IS_HIDE_BUTTON = "IS_HIDE_BUTTON"; // 是否隐藏按钮
        public static final String BUTTON_TEXT = "BUTTON_TEXT"; // 按钮文字
        public static final String BUTTON_GO_ACTIVITY = "BUTTON_GO_ACTIVITY"; // 按钮跳转的Activity，类的全名如：com.duowan.android.xianlu.MainActivity
    }

    /**
     *
     * 创建一个新的实例 ErrorNetworkPage.
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param activity
     *            Activity
     * @param paramMap
     *            参数map, 使用工具类生成: PageUtil.getErrorNetworkPageParamMap(...)
     */
    public ErrorNetworkPage(Activity activity, Map<String, Object> paramMap) {
        this.activity = activity;
        this.paramMap = paramMap;

        initView();
    }

    private void initView() {
        fErrorNetworkLl = (RelativeLayout) activity.findViewById(R.id.f_error_network_ll);
        fErrorNetworkIvImage = (ImageView) activity.findViewById(R.id.f_error_network_iv_image);
        fErrorNetworkTvComment = (TextView) activity.findViewById(R.id.f_error_network_tv_comment);
        fErrorNetworkBtn = (Button) activity.findViewById(R.id.f_error_network_btn);

        fErrorNetworkLl.setVisibility(View.GONE);

        String noticeMsg = MapUtil.getString(paramMap, PARAM_MAP_KEY.NOTICE_MSG, "");
        boolean isHideImage = MapUtil.getBooleanValue(paramMap, PARAM_MAP_KEY.IS_HIDE_IMAGE, false);
        int imgResId = MapUtil.getIntValue(paramMap, PARAM_MAP_KEY.IMAGE_RES_ID, -1);
        boolean isHideButton = MapUtil.getBooleanValue(paramMap, PARAM_MAP_KEY.IS_HIDE_BUTTON, false);
        String buttonText = MapUtil.getString(paramMap, PARAM_MAP_KEY.BUTTON_TEXT, "");

        if (isHideImage) {// 隐藏图片
            fErrorNetworkIvImage.setVisibility(View.GONE);
        }

        if (imgResId != -1) {
            Drawable drawable = activity.getResources().getDrawable(imgResId);
            fErrorNetworkIvImage.setImageDrawable(drawable);
        }

        if (AbStrUtil.isEmpty(noticeMsg)) {
            fErrorNetworkTvComment.setText(noticeMsg);
        }

        if (isHideButton) {// 隐藏按钮
            fErrorNetworkBtn.setVisibility(View.GONE);
        }

//        if (AbStrUtil.isEmpty(buttonText)) {
//            fErrorNetworkBtn.setText(buttonText);
//        }
    }

    /**
     * 显示网络连接失败页
     *
     * @Title: show
     * @Description: 显示网络连接失败页
     */
    public void show() {
        fErrorNetworkLl.setVisibility(View.VISIBLE);
    }

    /**
     * 显示网络连接失败页
     *
     * @Title: show
     * @Description: 显示网络连接失败页
     * @param needHideView
     *            需要隐藏的布局View
     */
    public void show(View needHideView) {
        if (needHideView != null) {
            needHideView.setVisibility(View.GONE);
        }
        fErrorNetworkLl.setVisibility(View.VISIBLE);
    }

    /**
     * 显示网络连接失败页
     *
     * @Title: show
     * @Description: 显示网络连接失败页
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

        fErrorNetworkLl.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏网络连接失败页
     *
     * @Title: show
     * @Description: 隐藏网络连接失败页
     */
    public void hide() {
        fErrorNetworkLl.setVisibility(View.GONE);
    }

    /**
     * 隐藏网络连接失败页
     *
     * @Title: show
     * @Description: 隐藏网络连接失败页
     * @param needShowView
     *            需要显示的布局View
     */
    public void hide(View needShowView) {
        if (needShowView != null) {
            needShowView.setVisibility(View.VISIBLE);
        }
        fErrorNetworkLl.setVisibility(View.GONE);
    }

    /**
     * 隐藏网络连接失败页
     *
     * @Title: hide
     * @Description: 隐藏网络连接失败页
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
        fErrorNetworkLl.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        // switch (v.getId()) {
        // case R.id.f_error_network_btn:
        // buttonClickJump();
        // break;
        // }
    }

    /**
     * 按钮跳转
     *
     * @Title: buttonClickJump
     * @Description: 按钮跳转
     */
    private void buttonClickJump() {
        String buttonGoActivity = MapUtil.getString(paramMap, PARAM_MAP_KEY.BUTTON_GO_ACTIVITY, "");
        if (AbStrUtil.isEmpty(buttonGoActivity)) {
            try {
                final Class goClass = Class.forName(buttonGoActivity);
                //UiSwitchUtil.toSpecifyActivity(activity, goClass);
            } catch (ClassNotFoundException e) {
                Log.e(tag, e.getMessage(), e);
            }
        } else {
            //UiSwitchUtil.toMainActivity(activity);
        }
    }

    public static String getString(final Map<?,?> map, final Object key) {
        if (map != null) {
            Object answer = map.get(key);
            if (answer != null) {
                return answer.toString();
            }
        }
        return null;
    }

}
