package com.mrwish.mybox.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.baselibrary.utils.AbSharedUtil;
import com.baselibrary.widget.RoundImageView;
import com.mrwish.mybox.Api.Constants;
import com.mrwish.mybox.R;
import com.mrwish.mybox.ui.activity.LoginActivity;
import com.mrwish.mybox.utils.App;

import java.util.ArrayList;
import java.util.List;


public class MyFragment extends BaseFragment {

    private GridView gv_class;
    private RoundImageView iv_head;
    private static MyFragment instance;

    public static MyFragment getInstance() {
        return instance;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        instance = this;
        iv_head = findView(R.id.iv_head);
        gv_class = findView(R.id.gv_class);
        gv_class.setSelector(new ColorDrawable(Color.TRANSPARENT));


    }

    @Override
    public void initListener() {
    }


    @Override
    public void initData() {
        List<String> classlist = new ArrayList<>();
        classlist.add("我的提货");
        classlist.add("我的历史");
        classlist.add("我的盒子");
        classlist.add("客服");
        classlist.add("投诉建议");
        classlist.add("物流通知");
        classlist.add("关于");
        if (classlist.size() > 0) {
            classAdapter = new ClassAdapter(getActivity(), classlist);
            gv_class.setAdapter(classAdapter);
        }
        iv_head.setOnClickListener(this);
    }


    @Override
    public void onClick(View v, int id) {
        switch (v.getId()) {
            case R.id.iv_head:
                Log.i("apih",  " BOX_TOKEN:" + AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN));
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
    public class ViewHolder {
        private TextView tv_name;
    }
    int typePosition=0;
    ClassAdapter classAdapter;
    public class ClassAdapter extends BaseAdapter {
        Context context;
        List<String> brandsList;
        LayoutInflater mInflater;
        public ClassAdapter(Context context, List<String> mList) {
            this.context = context;
            this.brandsList = mList;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return brandsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_my, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_name.setText(brandsList.get(position));

            return convertView;
        }
    }
}