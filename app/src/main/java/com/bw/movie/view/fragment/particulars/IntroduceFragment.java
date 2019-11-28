package com.bw.movie.view.fragment.particulars;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.DaoyAdapter;
import com.bw.movie.view.adapter.YuangyAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.LazyLoadFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class IntroduceFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.item_text_Jianj)
    TextView itemTextJianj;
    @BindView(R.id.item_rest_dy_list)
    RecyclerView itemRestDyList;
    @BindView(R.id.item_rest_yany_list)
    RecyclerView itemRestYanyList;
    Unbinder unbinder;
    @BindView(R.id.text_dy)
    TextView textDy;
    @BindView(R.id.text_yy)
    TextView textYy;

    @Override
    protected int initLayout() {
        return R.layout.item_introdce;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        SharedPreferences qq = getContext().getSharedPreferences("qq", App.MODE_PRIVATE);
        String sessionId = qq.getString("sessionId", null);
        int userId = qq.getInt("userId", 0);
        int movieId = qq.getInt("movieId", 0);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> smap = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        smap.put("movieId", movieId);
        Persenter persenter = new Persenter(this);
        persenter.doDetail(map, smap);

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
        DetilBean detilBean = (DetilBean) obj;
        final DetilBean.ResultBean result = detilBean.result;
        itemTextJianj.setText(result.summary);

        textDy.setText("导演("+result.movieDirector.size()+")");

        List<DetilBean.ResultBean.MovieDirectorBean> movieDirector = result.movieDirector;
        DaoyAdapter daoyAdapter=new DaoyAdapter(movieDirector);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        itemRestDyList.setLayoutManager(linearLayoutManager);
        itemRestDyList.setAdapter(daoyAdapter);
        textYy.setText("演员("+result.movieActor.size()+")");

        List<DetilBean.ResultBean.MovieActorBean> movieActor = result.movieActor;
        YuangyAdapter yuangyAdapter=new YuangyAdapter(movieActor);
        LinearLayoutManager linearLayou = new LinearLayoutManager(getContext());
        linearLayou.setOrientation(RecyclerView.HORIZONTAL);
        itemRestYanyList.setLayoutManager(linearLayou);
        itemRestYanyList.setAdapter(yuangyAdapter);
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
    public void onMovieCinema(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }
}
