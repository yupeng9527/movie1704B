package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.SoonMovieAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.LazyLoadFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
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
public class BeShaowFragment extends BaseFragment implements IViewContract.doView {

    @BindView(R.id.xrec_list)
    XRecyclerView xrecList;
    Unbinder unbinder;
    ArrayList<SoonMovieBean.ResultBean> list = new ArrayList<>();
    int page = 1;
    Map<String,Object> map=new HashMap<>();

    @Override
    protected int initLayout() {
        return R.layout.layout_beshao;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        list.clear();
        xrecList.setLoadingMoreEnabled(true);
        xrecList.setPullRefreshEnabled(true);
        xrecList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                Persenter persenter = new Persenter(BeShaowFragment.this);
                persenter.SoonMovieList(map,page);
                xrecList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                Persenter persenter = new Persenter(BeShaowFragment.this);
                persenter.SoonMovieList(map,page);
                xrecList.loadMoreComplete();
            }
        });
        Persenter persenter = new Persenter(BeShaowFragment.this);
        persenter.SoonMovieList(map,page);
        xrecList.refreshComplete();
    }

    @Override
    public void onLogCurress(Object obj) {

    }

    @Override
    public void onShapeCurress(Object obj) {
        List<SoonMovieBean.ResultBean> resultBeans = (List<SoonMovieBean.ResultBean>) obj;
        list.addAll(resultBeans);
        SoonMovieAdapter soonMovieAdapter=new SoonMovieAdapter(list,getContext());
        xrecList.setLayoutManager(new LinearLayoutManager(getContext()));
        xrecList.setAdapter(soonMovieAdapter);
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
