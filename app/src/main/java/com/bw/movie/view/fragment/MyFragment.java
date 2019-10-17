package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.zview.LazyLoadFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class MyFragment extends LazyLoadFragment {
    @BindView(R.id.imag_xinxi)
    ImageView imagXinxi;
    @BindView(R.id.r_r)
    RelativeLayout rR;
    @BindView(R.id.image_qwe)
    ImageView imageQwe;
    @BindView(R.id.text_qwe_name)
    TextView textQweName;
    @BindView(R.id.imag_name_space)
    ImageView imagNameSpace;
    @BindView(R.id.lin_lau)
    LinearLayout linLau;
    @BindView(R.id.imag_movie_span)
    ImageView imagMovieSpan;
    @BindView(R.id.lin_lay)
    LinearLayout linLay;
    @BindView(R.id.line_attention)
    LinearLayout lineAttention;
    @BindView(R.id.line_subscribe)
    LinearLayout lineSubscribe;
    @BindView(R.id.line_ticket)
    LinearLayout lineTicket;
    @BindView(R.id.line_seek_movie)
    LinearLayout lineSeekMovie;
    @BindView(R.id.line_discuss)
    LinearLayout lineDiscuss;
    @BindView(R.id.line_tickling)
    LinearLayout lineTickling;
    @BindView(R.id.line_install)
    LinearLayout lineInstall;
    Unbinder unbinder;

    @Override
    protected int initLayout() {
        return R.layout.item_fragment_my;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {

        imagNameSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.guide");
                startActivity(intent);
            }
        });

    }
    @Override
    public void fetchData() {
        EventBus.getDefault().register(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void activityFragment(GuideBean guideBean){
        GuideBean.ResultBean result = guideBean.result;
        GuideBean.ResultBean.UserInfoBean userInfo = result.userInfo;
        Glide.with(getContext())
                .load(userInfo.headPic)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.circleCropTransform())
                .into(imageQwe);
        textQweName.setText(userInfo.nickName);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
