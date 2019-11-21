package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaByBean;
import com.bw.movie.modle.bean.RegionListBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.AreaAdapter;
import com.bw.movie.view.adapter.FindCinemaAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/11/13
 * author:贺少伟(盗)
 * function:
 */
public class AreaFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.recy_left_view)
    RecyclerView recyLeftView;
    @BindView(R.id.recy_right_view)
    RecyclerView recyRightView;
    Unbinder unbinder;

    @Override
    protected int initLayout() {
        return R.layout.layout_area;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter= (Persenter) basePersenter;
        return persenter;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        Persenter persenter=new Persenter(this);
        persenter.doRegionList();
        return rootView;
    }
    @Override
    protected void initData() {

    }



    @Override
    public void onLogCurress(Object obj) {

    }

    @Override
    public void onShapeCurress(Object obj) {
        CinemaByBean cinemaByBean= (CinemaByBean) obj;
        List<CinemaByBean.ResultBean> result = cinemaByBean.result;
        FindCinemaAdapter findCinemaAdapter=new FindCinemaAdapter(result);
        recyRightView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyRightView.setAdapter(findCinemaAdapter);
        findCinemaAdapter.setAreaView(new FindCinemaAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                Intent intent=new Intent("com.bawei.Particulars");
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMyCurress(Object obj) {
        RegionListBean regionListBean= (RegionListBean) obj;
        List<RegionListBean.ResultBean> result = regionListBean.result;
        AreaAdapter areaAdapter=new AreaAdapter(result);
        recyLeftView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyLeftView.setAdapter(areaAdapter);
        areaAdapter.setAreaView(new AreaAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                recyRightView.setVisibility(View.VISIBLE);
                Persenter persenter=new Persenter(AreaFragment.this);
                persenter.dofindCinemaByRegion(id);

            }
        });
    }

    @Override
    public void onBannerCurress(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
