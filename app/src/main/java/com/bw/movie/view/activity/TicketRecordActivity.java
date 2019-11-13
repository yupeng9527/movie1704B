package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CnemaAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.fragment.Buy.BuyMovieFragment;
import com.bw.movie.view.fragment.Buy.BuyYeMovieFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketRecordActivity extends BaseActivity {


    @BindView(R.id.details_back)
    ImageView detailsBack;

    @BindView(R.id.buy_view_page)
    ViewPager buyViewPage;
    List<String> list = new ArrayList<>();
    List<Fragment> flist = new ArrayList<>();
    @BindView(R.id.buy_tab_la)
    TabLayout buyTabLa;

    @Override
    protected int initLayout() {
        return R.layout.activity_ticket_record;
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
        flist.add(new BuyMovieFragment());
        flist.add(new BuyYeMovieFragment());

        list.add("待付款");
        list.add("已付款");

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i);

            TabLayout.Tab tab = buyTabLa.newTab();
            if (tab != null) {
                tab.setText(title);
                buyTabLa.addTab(tab);
            }
        }

        buyTabLa.setupWithViewPager(buyViewPage);
        CnemaAdapter cnemaAdapter = new CnemaAdapter(getSupportFragmentManager(), flist, list);
        cnemaAdapter.notifyDataSetChanged();
        buyViewPage.setAdapter(cnemaAdapter);
    }
}
