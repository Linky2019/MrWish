package com.mrwish.mybox.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baselibrary.utils.AbSharedUtil;
import com.mrwish.mybox.R;
import com.mrwish.mybox.ui.fragment.BagFragment;
import com.mrwish.mybox.ui.fragment.HomeFragment;
import com.mrwish.mybox.ui.fragment.MyFragment;
import com.mrwish.mybox.utils.App;
import com.mrwish.mybox.utils.BottomBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeadVisibility(false);
        intiView();
    }

    void intiView() {
        bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#333333")
                .addItem(HomeFragment.class,
                        "首页",
                        R.mipmap.home_icon,
                        R.mipmap.home_icon)
                .addItem(BagFragment.class,
                        "背包",
                        R.mipmap.yuan,
                        R.mipmap.yuan)
                .addItem(MyFragment.class,
                        "我的",
                        R.mipmap.my_icon,
                        R.mipmap.my_icon)

                .build();


    }

    //改写物理按键——返回的逻辑
    private boolean isExit = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 67) return false;//67为软键盘删除键事件
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            App.getInstance().exitAllActivity();
        }
        return false;
    }

}