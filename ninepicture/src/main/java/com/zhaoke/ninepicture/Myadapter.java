package com.zhaoke.ninepicture;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * author zhaoke
 * date: 2018/12/3
 */
public class Myadapter extends BaseQuickAdapter<UserViewInfo, BaseViewHolder> {
    private Context mContext;
    List<UserViewInfo> list;

    public Myadapter(int layoutResId, List<UserViewInfo> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserViewInfo item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .into((ImageView) helper.getView(R.id.iv));

    }
}
