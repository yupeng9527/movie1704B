package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinmeaKeyBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinemaKeyAdapter;
import com.bw.movie.view.adapter.CnemaAdapter;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.CustomViewPager;
import com.bw.movie.view.zview.LazyLoadFragment;
import com.bw.movie.view.zview.SearchView;

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
public class ShapeFragment extends LazyLoadFragment implements IViewContract.doView {
    @BindView(R.id.sear_chv)
    SearchView searChv;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    Unbinder unbinder;
    List<String> list = new ArrayList<>();
    List<Fragment> flist = new ArrayList<>();
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.relist_view_ss)
    RecyclerView relistViewSs;
    @BindView(R.id.linter_ss)
    LinearLayout linterSs;
    @BindView(R.id.l_ss_layout)
    LinearLayout lSsLayout;

    @Override
    protected int initLayout() {
        return R.layout.item_fragment_shape;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        searChv.setOnIntersen(new SearchView.OnIntersen() {
            @Override
            public void onFinis(String str) {
               lSsLayout.setVisibility(View.GONE);
               linterSs.setVisibility(View.VISIBLE);
                Map<String,Object> map=new HashMap<>();
                map.put("cinemaName",str);
                map.put("page",1);
                map.put("count",10);
               Persenter persenter=new Persenter(ShapeFragment.this);
               persenter.doCinemaByKey(map);
            }

            @Override
            public void onSs(String str) {
                lSsLayout.setVisibility(View.VISIBLE);
                linterSs.setVisibility(View.GONE);
            }
        });
        flist.add(new RecommendFragment());
        flist.add(new NearByFragment());
        flist.add(new AreaFragment());

        list.add("推荐影院");
        list.add("附近影院");
        list.add("海淀区");

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i);
            TabLayout.Tab tab = tabLayout.newTab();
            if (tab != null) {
                tab.setText(title);
                tabLayout.addTab(tab);
            }
        }
        viewPager.setScanScroll(true);
        tabLayout.setupWithViewPager(viewPager);
        CnemaAdapter cnemaAdapter = new CnemaAdapter(getChildFragmentManager(), flist, list);
        cnemaAdapter.notifyDataSetChanged();
        viewPager.setAdapter(cnemaAdapter);
    }

    @Override
    public void fetchData() {

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
        CinmeaKeyBean cinmeaKeyBean= (CinmeaKeyBean) obj;
        List<CinmeaKeyBean.ResultBean> result = cinmeaKeyBean.result;
        CinemaKeyAdapter cinemaKeyAdapter=new CinemaKeyAdapter(result);
        relistViewSs.setLayoutManager(new LinearLayoutManager(getContext()));
        relistViewSs.setAdapter(cinemaKeyAdapter);
        cinemaKeyAdapter.setAreaView(new CinemaKeyAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                Intent intent=new Intent("com.bawei.Particulars");
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLogExurr(String str) {

    }
}
