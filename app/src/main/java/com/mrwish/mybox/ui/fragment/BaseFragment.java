package com.mrwish.mybox.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mrwish.mybox.utils.MyDialog;


public abstract class BaseFragment extends Fragment implements IUIOperation{
    protected String  mArgs;

    /** 管理Fragment的Activity */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //设置导航栏字体为黑色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                getActivity().getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }catch (Exception e){

        }
                //接收传递过来的参数
        Bundle arguments = getArguments();
        if (arguments != null) {
            mArgs = arguments.getString("args");
        }
    }

    /** Fragment显示的布局 */
    public View mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mRoot == null) {
            mRoot = inflater.inflate(getLayoutRes(), null);

            // 查找布局中的所有的button(ImageButton),并设置点击事件
//            Utils.findButtonAndSetOnClickListener(mRoot, this);

            initView();
            initListener();
            initData();
        } else {
            // 解除mRoot与其父控件的关系
            unbindWidthParent(mRoot);
        }

        return mRoot;
    }


    /**
     * 解除父子控件关系
     *
     * @param view
     */
    public void unbindWidthParent(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }

    /** 查找子控件，可以省略强转 */
    public <T> T findView(int id) {
        @SuppressWarnings("unchecked")
        T view = (T) mRoot.findViewById(id);
        return view;
    }


    @Override
    public void onClick(View v) {
        onClick(v, v.getId());
    }

    protected void showLoadDialog() {
        try {
            MyDialog.show(getActivity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void dismissLoadDialog() {
        try {
            MyDialog.dismiss(getActivity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }


}
