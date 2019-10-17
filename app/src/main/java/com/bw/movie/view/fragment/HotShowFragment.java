package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.MovieAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.LazyLoadFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class HotShowFragment extends LazyLoadFragment implements IViewContract.doView {
    @BindView(R.id.xrec_list)
    XRecyclerView xrecList;
    Unbinder unbinder;
    int page = 1;
    ArrayList<MoVieListBean.ResultBean> list = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected int initLayout() {
        return R.layout.layout_hotshaow;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        movieAdapter.notifyDataSetChanged();
        return persenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {


    }
    @Override
    public void fetchData() {
        list.clear();
        xrecList.setLoadingMoreEnabled(true);
        xrecList.setPullRefreshEnabled(true);
        xrecList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                Persenter persenter = new Persenter(HotShowFragment.this);
                persenter.doMovieList(page);
                xrecList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                Persenter persenter = new Persenter(HotShowFragment.this);
                persenter.doMovieList(page);

                xrecList.loadMoreComplete();
            }
        });
        Persenter persenter = new Persenter(HotShowFragment.this);
        persenter.doMovieList(page);
        xrecList.refreshComplete();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLogCurress(Object obj) {
        List<MoVieListBean.ResultBean> resultBeans = (List<MoVieListBean.ResultBean>) obj;
        list.addAll(resultBeans);
        Log.i("qq", "onLogCurress: " + list.size());

        movieAdapter = new MovieAdapter(list);
        movieAdapter.notifyDataSetChanged();
        xrecList.setLayoutManager(new LinearLayoutManager(getContext()));
        xrecList.setAdapter(movieAdapter);
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
