package com.mrwish.mybox.ui.fragment;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baselibrary.widget.pulltorefresh.PullToRefreshBase;
import com.baselibrary.widget.pulltorefresh.PullToRefreshScrollView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrwish.mybox.Api.Constants;
import com.mrwish.mybox.R;
import com.mrwish.mybox.model.BaseModel;
import com.mrwish.mybox.ui.adapter.BoxAdapter;
import com.mrwish.mybox.utils.CsGridLayoutManager;


import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment {

    private PullToRefreshScrollView pullToRefreshScrollView;
    private RecyclerView recyclerView;
    private ArrayList<BaseModel> dataList = new ArrayList<>();
    private BoxAdapter boxAdapter;
    private String mCategoryId = "1";
    private TextView tv_null;
    private boolean isFirstVisible=true;
    public static ListFragment newInstance(String gc_id) {
        ListFragment newFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("gc_id", gc_id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    private ImageView iv_top;
    private ScrollView scrollView;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list;
    }


    @Override
    public void initView() {

        if (getArguments() != null) {
            mCategoryId = getArguments().getString("gc_id");
        }
        tv_null = findView(R.id.tv_null);
        iv_top = findView(R.id.iv_top);
        recyclerView = findView(R.id.recyclerview);

        recyclerView.setNestedScrollingEnabled(false);
        CsGridLayoutManager localGridLayoutManager = new CsGridLayoutManager(getActivity(), 2);//每行显示item个数
        recyclerView.setLayoutManager(localGridLayoutManager);
        try {
            List<BaseModel> addList = new Gson().fromJson(Constants.data, new TypeToken<ArrayList<BaseModel>>() {}.getType());
            if (addList != null) {
                tv_null.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                dataList.clear();
                dataList.addAll(addList);
                boxAdapter = new BoxAdapter(getActivity(), dataList);
                recyclerView.setAdapter(boxAdapter);
            }
        }catch (Exception e){}
        pullToRefreshScrollView = findView(R.id.scrollView);
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);


        //3.设置监听事件
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //刷新
               getMoreData(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //加载更多
               getMoreData(true);
            }
        });
        scrollView = pullToRefreshScrollView.getRefreshableView();
        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回顶部
                scrollView.smoothScrollTo(0,0);
                iv_top.setVisibility(View.GONE);
            }
        });
        pullToRefreshScrollView.getRefreshableView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_MOVE){
                    //可以监听到ScrollView的滚动事件
                    double alpha = (double)pullToRefreshScrollView.getRefreshableView().getScrollY();
                    if (alpha>1000){
                        iv_top.setVisibility(View.VISIBLE);
                    }else {
                        iv_top.setVisibility(View.GONE);
                    }
                }
                return false;
            }
        });
    }

    public void getMoreData(boolean isMore){
        if(pullToRefreshScrollView!=null){
            pullToRefreshScrollView.onRefreshComplete();
        }
//        if(isMore){
//            pageIndex++;
//        }else {
//            pageIndex = 1;
//            time = System.currentTimeMillis();
//        }
//
//        goodClassHelper.getBaicai(mCategoryId,"14",""+pageIndex,time+"",sortType,sortMode,"1");
//        if (!AbStrUtil.isEmpty(mCategoryId)) {
//            if (mCategoryId.equals("99999999")) {
//                lay_paixu.setVisibility(GONE);
//            } else {
//                lay_paixu.setVisibility(VISIBLE);
//            }
//        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getArguments() != null) {
                mCategoryId = getArguments().getString("gc_id");//此处防止加载三次导致分类出错
            }
            // 相当于onResume()方法--获取焦点
            if(isFirstVisible){
//                App app = App.getInstance();
//                if(app.getIndex()!=0){
//                    categoryHelper = new CategoryHelper(this);
//                    categoryHelper.getChildCategory(mCategoryId,"14",app.getIndex()+"",time+"",sort_type,sort_mode,"1");
//                }else{
//                    categoryHelper = new CategoryHelper(this);
//                    categoryHelper.getChildCategory(mCategoryId,"14",app.getIndex()+"",time+"",sort_type,sort_mode,"1");
//                }
//                    categoryHelper.getTopChildCategory(mCategoryId);
            }
            isFirstVisible=false;
        } else {
            // 相当于onpause()方法---失去焦点
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v, int id) {
         switch (v.getId()){
//             case R.id.ll_more:
//                 if(specialList!=null && specialList.size()>=2){
//                     if(specialList!=null){
//                         for (int i = 2; i < specialList.size(); i++) {
//                             final CategorySpecialInfo model = specialList.get(i);
////                             createSpecialView(model);
//                         }
//                     }
//                 }

//                 break;
         }
    }


}

