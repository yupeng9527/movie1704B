package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.DateListBean;
import com.bw.movie.modle.bean.ScheduleListBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.ScheduleListAdapter;
import com.bw.movie.view.adapter.ScheduleTabAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.tab_lay)
    TabLayout tabLay;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int did;



    @Override
    protected int initLayout() {
        return R.layout.activity_schedule;
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
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        did = intent.getIntExtra("did", 0);

        Persenter persenter = new Persenter(this);
        persenter.doDateList();
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
        DateListBean dateListBean = (DateListBean) obj;
        final List<String> result = dateListBean.result;
        for (int i = 0; i < result.size(); i++) {
            final String s = result.get(i);
            TabLayout.Tab tab = tabLay.newTab();
            if (tab != null) {
                tab.setText(s);
                tabLay.addTab(tab);
            }
        }
        ScheduleTabAdapter scheduleTabAdapter = new ScheduleTabAdapter(getSupportFragmentManager(), result,did);
        tabLay.setupWithViewPager(viewPager);
        viewPager.setAdapter(scheduleTabAdapter);



    }

    @Override
    public void onLogExurr(String str) {

    }


}
