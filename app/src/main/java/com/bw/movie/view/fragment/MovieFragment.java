package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.HotAdapter;
import com.bw.movie.view.adapter.HtoAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class MovieFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.rec_list)
    RecyclerView recList;
    Unbinder unbinder;
    int page = 1;

    @Override
    protected int initLayout() {
        return R.layout.layout_movie;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        Persenter persenter = new Persenter(MovieFragment.this);
        persenter.HotMovieList(page);
    }

    @Override
    public void onLogCurress(Object obj) {

    }

    @Override
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {
        List<HotBean.ResultBean> result = (List<HotBean.ResultBean>) obj;

        HtoAdapter hotAdapter=new HtoAdapter(result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        recList.setAdapter(hotAdapter);
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
