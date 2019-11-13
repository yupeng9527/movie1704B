package com.bw.movie.view.activity.myfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CnemaAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.fragment.Buy.BuyMovieFragment;
import com.bw.movie.view.fragment.Buy.BuyYeMovieFragment;
import com.bw.movie.view.fragment.attention.CinemaAttentionFragment;
import com.bw.movie.view.fragment.attention.MovieAttentionFragment;
import com.bw.movie.view.zview.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttentionActivity extends BaseActivity {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    List<String> list = new ArrayList<>();
    List<Fragment> flist = new ArrayList<>();
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    @Override
    protected int initLayout() {
        return R.layout.activity_attention;
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
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        flist.add(new MovieAttentionFragment());
        flist.add(new CinemaAttentionFragment());

        list.add("电影");
        list.add("影院");

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i);

            TabLayout.Tab tab = tabLayout.newTab();
            if (tab != null) {
                tab.setText(title);
                tabLayout.addTab(tab);
            }
        }

        tabLayout.setupWithViewPager(viewPager);
        CnemaAdapter cnemaAdapter = new CnemaAdapter(getSupportFragmentManager(), flist, list);
        cnemaAdapter.notifyDataSetChanged();
        viewPager.setAdapter(cnemaAdapter);
    }
}
