package com.mrwish.mybox.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mrwish.mybox.Api.Constants;
import com.mrwish.mybox.R;
import com.mrwish.mybox.model.BaseModel;

import java.util.List;


/**
 * 盒子列表
 */
public class BoxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseModel> dataList;
    private Context mContext;

    public BoxAdapter(Context context, List<BaseModel> mumuList) {
        this.mContext = context;
        this.dataList = mumuList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_box, parent, false);

        final ViewHolder menuViewHolder = new ViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        try {
            final BaseModel goodInfo = dataList.get(position);
            Glide.with(mContext).load(Constants.TEST_PIC).into(((ViewHolder) holder).iv_pic);
            ((ViewHolder) holder).tv_title.setText("我的盒子");
            ((ViewHolder) holder).tv_money.setText("￥99");
            ((ViewHolder) holder).lay_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mContext.startActivity(new Intent(mContext, InterestListActivity.class));
                }
            });
        } catch (Exception e) {
        }

    }


    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_money;
        public ImageView iv_pic;
        private LinearLayout lay_view;

        public ViewHolder(View itemView) {
            super(itemView);
            lay_view = itemView.findViewById(R.id.lay_view);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }


}

