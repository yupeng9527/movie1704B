package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.modle.bean.MovieByKeyBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.FragAdapter;
import com.bw.movie.view.adapter.MovieKeyAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.fragment.BeShaowFragment;
import com.bw.movie.view.fragment.HotShowFragment;
import com.bw.movie.view.fragment.MovieFragment;

import com.bw.movie.view.zview.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity implements IViewContract .doView{


    List<String> slist = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.tab_lay)
    TabLayout tabLay;

    @BindView(R.id.sear_view)
    SearchView searView;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.recy_layout_ss)
    RecyclerView recyLayoutSs;
    @BindView(R.id.linear_layout_ss)
    LinearLayout linearLayoutSs;

    @Override
    protected int initLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        searView.setOnIntersen(new SearchView.OnIntersen() {
            @Override
            public void onFinis(String str) {
                linearLayoutSs.setVisibility(View.GONE);
                recyLayoutSs.setVisibility(View.VISIBLE);
                Map<String, Object> map = new HashMap<>();
                map.put("keyword", str);
                map.put("page", 1);
                map.put("count", 10);
                Persenter persenter=new Persenter(ShowActivity.this);
                persenter.doMovieByKey(map);
            }

            @Override
            public void onSs(String str) {
                linearLayoutSs.setVisibility(View.VISIBLE);
                recyLayoutSs.setVisibility(View.GONE);
            }
        });
        setTranslucent(this);
        list.add(new HotShowFragment());
        list.add(new BeShaowFragment());
        list.add(new MovieFragment());
        slist.add("正在热映");
        slist.add("即将上映");
        slist.add("热门电影");
        for (int i = 0; i < slist.size(); i++) {
            String title = slist.get(i);
            TabLayout.Tab tab = tabLay.newTab();
            if (tab != null) {
                tab.setText(title);
                tabLay.addTab(tab);
            }
        }
        tabLay.setupWithViewPager(viewPage);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), list, slist);
        fragAdapter.notifyDataSetChanged();
        viewPage.setAdapter(fragAdapter);

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
        MovieByKeyBean movieByKeyBean= (MovieByKeyBean) obj;
        List<MovieByKeyBean.ResultBean> result = movieByKeyBean.result;
        recyLayoutSs.setLayoutManager(new LinearLayoutManager(this));
        MovieKeyAdapter movieKeyAdapter=new MovieKeyAdapter(result);
        recyLayoutSs.setAdapter(movieKeyAdapter);
        movieKeyAdapter.setAreaView(new MovieKeyAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                Intent intent1 = new Intent("com.bawei.SelectMovie");
                intent1.putExtra("movieId", id);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onLogExurr(String str) {

    }
}
