package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinemaAdapter;
import com.bw.movie.view.adapter.CnemaAdapter;
import com.bw.movie.view.adapter.RecommedAdapter;
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
public class NearByFragment extends LazyLoadFragment implements IViewContract.doView {
    @BindView(R.id.rec_list)
    RecyclerView recList;
    Unbinder unbinder;

    @Override
    protected int initLayout() {
        return R.layout.layout_cinema;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void fetchData() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",18);
        map.put("sessionId","15320748258726");
        Map<String,Object> omap=new HashMap<>();
        omap.put("longitude","116.30551391385724");
        omap.put("latitude","40.04571807462411");
        omap.put("page",1);
        omap.put("count",10);
        Persenter persenter=new Persenter(this);
        persenter.Cinema(map,omap);
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
        List<CinemaBean.ResultBean> resultBeans= (List<CinemaBean.ResultBean>) obj;
        CinemaAdapter cinemaAdapter=new CinemaAdapter(resultBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        recList.setAdapter(cinemaAdapter);
        cinemaAdapter.setAreaView(new CinemaAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                Intent intent=new Intent("com.bawei.Particulars");
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
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
