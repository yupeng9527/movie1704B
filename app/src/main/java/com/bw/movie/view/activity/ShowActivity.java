package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.FragAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.fragment.BeShaowFragment;
import com.bw.movie.view.fragment.HotShowFragment;
import com.bw.movie.view.fragment.MovieFragment;
import com.bw.movie.view.zview.CustomViewPager;
import com.bw.movie.view.zview.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity {


    List<String> slist = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.tab_lay)
    TabLayout tabLay;

    @BindView(R.id.sear_view)
    SearchView searView;
    @BindView(R.id.view_page)
    CustomViewPager viewPage;

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


}
