package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.activity.FeedActivity;
import com.bw.movie.view.activity.GuideActivity;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;
import com.bw.movie.view.zview.LazyLoadFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class MyFragment extends BaseFragment implements IViewContract.doView {
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
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        int weq = sp.getInt("weq", 0);
        String email = sp.getString("email", "");
        String pwd = sp.getString("pwd", "");
        String decrypt = EncryptUtil.decrypt(pwd);
        if (weq==1){

            Map<String,String> map=new HashMap<>();
            map.put("email",email);
            map.put("pwd",decrypt);
            Persenter persenter=new Persenter(this);
            persenter.doGuild(map);
        }
        String headPic = sp.getString("headPic", "");
        String nickName = sp.getString("nickName", "");
        Glide.with(getContext())
                .load(headPic)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.circleCropTransform())
                .into(imageQwe);
        textQweName.setText(nickName);
        lineTickling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.feed");
                startActivity(intent);
            }
        });
        lineTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.TicketRecord");
                startActivity(intent);
            }
        });
//        SharedPreferences pp = getContext().getSharedPreferences("qaz", Context.MODE_PRIVATE);
//        if (pp!=null){
//            String nickName1 = pp.getString("nickName", null);
//            String headPic1 = pp.getString("headPic", null);
//            Glide.with(getContext())
//                    .load(headPic1)
//                    .error(R.mipmap.ic_launcher)
//                    .placeholder(R.mipmap.ic_launcher_round)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(imageQwe);
//            textQweName.setText(nickName1);
//        }
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


    @Override
    public void onLogCurress(Object obj) {
        GuideBean guideBean= (GuideBean) obj;
        SharedPreferences sp = getContext().getSharedPreferences("qaz", Context.MODE_PRIVATE);
        sp.edit()
                .putInt("userId",guideBean.result.userId)
                .putString("sessionId",guideBean.result.sessionId)
                .commit();
        Log.i("qqq", "onLogCurress: "+guideBean.result.userId);
        Log.i("qqq", "onLogCurress: "+guideBean.result.sessionId);
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
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {

    }

    @Override
    public void onBannerCurress(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }
}
