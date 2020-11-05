package com.mrwish.mybox.ui.fragment;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.baselibrary.utils.AbSharedUtil;
import com.baselibrary.utils.AbToastUtil;
import com.google.android.material.tabs.TabLayout;
import com.mrwish.mybox.Api.Constants;
import com.mrwish.mybox.R;
import com.mrwish.mybox.model.FragmentInfo;
import com.mrwish.mybox.presenters.HomeHelper;
import com.mrwish.mybox.presenters.viewinterface.HomeView;
import com.mrwish.mybox.ui.adapter.ViewPagerAdapter;
import com.mrwish.mybox.utils.ImageCycleView;
import com.mrwish.mybox.utils.MyViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements HomeView {

    ImageCycleView iv_topimg;
    private TextView tv_top;
    ArrayList<String> mImageUrl = new ArrayList<>();
    private MyViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    public TextView toBeReceivedTextView;
    HomeHelper homeHelper;
    private ArrayList<FragmentInfo> mFragmentLists = new ArrayList();
    private static HomeFragment instance;

    public static HomeFragment getInstance() {
        return instance;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        instance = this;
        tv_top = findView(R.id.tv_top);
        iv_topimg = findView(R.id.iv_topimg);
        tabLayout = findView(R.id.tabLayout);
        viewPager = findView(R.id.viewPager);
//        List<String> classlist = new ArrayList<>();
//        classlist.add("全部");
//        classlist.add("其他");
//        classlist.add("衣服");
//        classlist.add("科技");
//        classlist.add("手办");
//        classlist.add("潮流");
//        classlist.add("彩妆");
//        classlist.add("玩具");
//        classlist.add("测试");
//
//        //从1开始，跳过全部
//        for (int i = 0; i <classlist.size(); i++) {
////            BaseModel temp = categoryMenuList.get(i);
//            FragmentInfo fragmentInfo = getFragmengInfo(ListFragment.newInstance(i+""), i+"", classlist.get(i), i);
//            mFragmentLists.add(fragmentInfo);
//
//        }
//        adapter = new ViewPagerAdapter(getChildFragmentManager(), mFragmentLists);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        for (int i = 0; i <classlist.size(); i++) {
//            tabLayout.getTabAt(i).setCustomView(R.layout.main_top_item);
//            toBeReceivedTextView = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tv_top_item);
//            toBeReceivedTextView.setText(classlist.get(i));
//            if (i==0){
//                //默认选中第一项
//                toBeReceivedTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//                toBeReceivedTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//            }
//        }
        tabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.transparent)));/*去除tablayout 子tab点击时的黑色背景*/
    }
    private FragmentInfo getFragmengInfo(Fragment fragment, String id, String title, int viewPageIndex) {
        FragmentInfo fragmentInfo = new FragmentInfo();
        fragmentInfo.setFragment(fragment);
        fragmentInfo.setId(id);
        fragmentInfo.setTitle(title);
        fragmentInfo.setViewpageIndex(viewPageIndex);
        return fragmentInfo;
    }
    @Override
    public void initListener() {
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            // TODO 单击图片处理事件
            Log.i("apih","p--"+position);
        }

        @Override
        public void displayImage(int po, ImageView imageView) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void initData() {
        homeHelper = new HomeHelper(this);
        homeHelper.getBanner();
        homeHelper.getCategory();
//        mImageUrl.add(R.mipmap.banner01);
//        mImageUrl.add(R.mipmap.banner02);
//        mImageUrl.add(R.mipmap.banner03);
//        mImageUrl.add(R.mipmap.banner04);
//        iv_topimg.setImageResources(mImageUrl, mAdCycleViewListener);//轮播图

    }


    @Override
    public void onClick(View v, int id) {
        switch (v.getId()) {
            case R.id.tv_top:
                break;
        }
    }

    @Override
    public void onBannerView(String result) {
        if (result!=null) {
            try {
//                JSONObject object = new JSONObject(result);
//                int code = object.optInt("code");
//                String msg = object.optString("message");
//                AbToastUtil.showToast(mContext,msg);
//                if (AbResult.RESULT_OK == code){
//                    String data = object.optString("data");
//                    JSONObject dataobj = new JSONObject(data);
//
//                }
                JSONArray list = new JSONArray(result);
                for (int i=0;i<list.length();i++){
                    JSONObject jsonObject = list.getJSONObject(i);
                    mImageUrl.add(jsonObject.getString("image"));
                }
                iv_topimg.setImageResources(mImageUrl, mAdCycleViewListener);//轮播图
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }/* else {
            AbToastUtil.showToast(getActivity(),getResources().getString(R.string.time_out));
        }*/
    }

    @Override
    public void onCategoryView(String result) {
        if (result!=null) {
            try {

                JSONArray list = new JSONArray(result);
                for (int i = 0; i <list.length(); i++) {
//            BaseModel temp = categoryMenuList.get(i);
                    JSONObject jsonObject = list.getJSONObject(i);
                    FragmentInfo fragmentInfo = getFragmengInfo(ListFragment.newInstance(i+""), jsonObject.getString("id"), jsonObject.getString("name"), i);
                    mFragmentLists.add(fragmentInfo);
                }
                adapter = new ViewPagerAdapter(getChildFragmentManager(), mFragmentLists);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}