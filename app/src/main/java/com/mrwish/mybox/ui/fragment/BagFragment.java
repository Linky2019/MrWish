package com.mrwish.mybox.ui.fragment;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrwish.mybox.Api.Constants;
import com.mrwish.mybox.R;
import com.mrwish.mybox.model.BaseModel;
import com.mrwish.mybox.utils.ImageCycleView;

import java.util.ArrayList;
import java.util.List;


public class BagFragment extends BaseFragment {

    private TextView tv_open,tv_zhihuan,tv_duihuan;
    private View v_open,v_zhihuan,v_duihuan;
    private LinearLayout lay_open,lay_zhihuan,lay_duihuan;
    private ViewPager viewPager;
    private ArrayList<View> pageview;
    // 滚动条初始偏移量
    private int offset = 0;
    // 当前页编号
    private int currIndex = 0;
    //一倍滚动量
    private int one;
    private static BagFragment instance;

    public static BagFragment getInstance() {
        return instance;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bag;
    }

    @Override
    public void initView() {
        instance = this;
        tv_open = findView(R.id.tv_open);
        tv_zhihuan = findView(R.id.tv_zhihuan);
        tv_duihuan = findView(R.id.tv_duihuan);
        v_open = findView(R.id.v_open);
        v_zhihuan = findView(R.id.v_zhihuan);
        v_duihuan = findView(R.id.v_duihuan);
        lay_open = findView(R.id.lay_open);
        lay_zhihuan = findView(R.id.lay_zhihuan);
        lay_duihuan = findView(R.id.lay_duihuan);
        viewPager = findView(R.id.viewPager);
        LayoutInflater inflater =getLayoutInflater();
        View view1 = inflater.inflate(R.layout.viewpage_bag01, null);
        View view2 = inflater.inflate(R.layout.viewpage_bag01, null);
        View view3 = inflater.inflate(R.layout.viewpage_bag01, null);
        pageview =new ArrayList<View>();
        //添加想要切换的界面
        pageview.add(view1);
        pageview.add(view2);
        pageview.add(view3);

        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter(){

            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageview.size();
            }

            @Override
            //判断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }
            //使从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageview.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1){
                ((ViewPager)arg0).addView(pageview.get(arg1));
                return pageview.get(arg1);
            }
        };
        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //设置viewPager的初始界面为第一个界面
        viewPager.setCurrentItem(currIndex);
        //添加切换界面的监听器
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    /**
                     * TranslateAnimation的四个属性分别为
                     * float fromXDelta 动画开始的点离当前View X坐标上的差值
                     * float toXDelta 动画结束的点离当前View X坐标上的差值
                     * float fromYDelta 动画开始的点离当前View Y坐标上的差值
                     * float toYDelta 动画开始的点离当前View Y坐标上的差值
                     **/
                    animation = new TranslateAnimation(one, 0, 0, 0);
                    setViewStatus(tv_open,v_open);
                    break;
                case 1:
                    animation = new TranslateAnimation(offset, one, 0, 0);
                    setViewStatus(tv_zhihuan,v_zhihuan);
                    break;
                case 2:
                    animation = new TranslateAnimation(offset, one, 0, 0);
                    setViewStatus(tv_duihuan,v_duihuan);
                    break;
            }
            //arg0为切换到的页的编码
            currIndex = arg0;
            // 将此属性设置为true可以使得图片停在动画结束时的位置
            animation.setFillAfter(true);
            //动画持续时间，单位为毫秒
            animation.setDuration(200);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    @Override
    public void initListener() {

    }


    @Override
    public void initData() {
        lay_open.setOnClickListener(this);
        lay_zhihuan.setOnClickListener(this);
        lay_duihuan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v, int id) {
        switch (v.getId()) {
            case R.id.lay_open:
                viewPager.setCurrentItem(0);
                break;
            case R.id.lay_zhihuan:
                viewPager.setCurrentItem(1);
                break;
            case R.id.lay_duihuan:
                viewPager.setCurrentItem(2);
                break;
        }
    }
    private void setViewStatus(TextView textView,View view){
        tv_open.setTextColor(getResources().getColor(R.color.text_gray));
        tv_open.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv_zhihuan.setTextColor(getResources().getColor(R.color.text_gray));
        tv_zhihuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        tv_duihuan.setTextColor(getResources().getColor(R.color.text_gray));
        tv_duihuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        textView .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setTextColor(getResources().getColor(R.color.text_main));
        v_open.setVisibility(View.INVISIBLE);
        v_duihuan.setVisibility(View.INVISIBLE);
        v_zhihuan.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);

    }
}