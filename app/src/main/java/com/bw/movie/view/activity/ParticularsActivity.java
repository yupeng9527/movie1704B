package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaInfoBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.FragAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.fragment.ShapeFragment;
import com.bw.movie.view.fragment.YMovieFragment;
import com.bw.movie.view.fragment.particular.CinemaDetailsFragment;
import com.bw.movie.view.fragment.particular.CinemaEvaluateFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticularsActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_name_name)
    TextView textNameName;
    @BindView(R.id.text_label)
    TextView textLabel;
    @BindView(R.id.liner_address)
    LinearLayout linerAddress;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.but_pq)
    Button butPq;
    List<Fragment> list = new ArrayList<>();
    List<String> llist = new ArrayList<>();
    @BindView(R.id.tab_lay)
    TabLayout tabLay;
    @BindView(R.id.details_btn)
    ImageView detailsBtnImg;
    @BindView(R.id.details_btn_false)
    ImageView detailsBtnImgFalse;
    private Map<String, Object> map;
    private int id;

    @Override
    protected int initLayout() {
        return R.layout.activity_particulars;
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
        id = intent.getIntExtra("id", 0);
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        sp.edit()
                .putInt("id", id).commit();
        Persenter persenter = new Persenter(this);
        persenter.doCinemaInfo(map, id);
        list.add(new CinemaDetailsFragment());
        list.add(new CinemaEvaluateFragment());
        llist.add("影院详情");
        llist.add("影院评价");
        for (int i = 0; i < llist.size(); i++) {
            String title = llist.get(i);
            TabLayout.Tab tab = tabLay.newTab();
            if (tab != null) {
                tab.setText(title);
                tabLay.addTab(tab);
            }
        }

        tabLay.setupWithViewPager(viewPager);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), list, llist);
        fragAdapter.notifyDataSetChanged();
        viewPager.setAdapter(fragAdapter);

    }

    @Override
    public void onLogCurress(Object obj) {
        CinemaInfoBean cinemaInfoBean = (CinemaInfoBean) obj;
        final CinemaInfoBean.ResultBean result = cinemaInfoBean.result;
        butPq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int did = result.id;
                Intent intent=new Intent("com.bawei.Schedule");
                intent.putExtra("did",did);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        textNameName.setText(result.name);
        textLabel.setText(result.label);
        if (result.followCinema == 1) {
            detailsBtnImg.setVisibility(View.GONE);
            detailsBtnImgFalse.setVisibility(View.VISIBLE);
        } else if (result.followCinema == 2) {
            detailsBtnImg.setVisibility(View.VISIBLE);
            detailsBtnImgFalse.setVisibility(View.GONE);
        }
        detailsBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persenter persenter=new Persenter(ParticularsActivity.this);
                persenter.onFollow(map,id);

                detailsBtnImg.setVisibility(View.GONE);
                detailsBtnImgFalse.setVisibility(View.VISIBLE);
            }
        });
        detailsBtnImgFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persenter persenter=new Persenter(ParticularsActivity.this);
                persenter.onCancel(map,id);
                detailsBtnImg.setVisibility(View.VISIBLE);
                detailsBtnImgFalse.setVisibility(View.GONE);

            }
        });
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
