package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.ViewAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.fragment.MyFragment;
import com.bw.movie.view.fragment.ShapeFragment;
import com.bw.movie.view.fragment.YMovieFragment;
import com.bw.movie.view.zview.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrincipalActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.image_movei_dj)
    ImageView imageMoveiDj;
    @BindView(R.id.lin_movei)
    LinearLayout linMovei;
    @BindView(R.id.image_cinem_dj)
    ImageView imageCinemDj;
    @BindView(R.id.lin_cinem)
    LinearLayout linCinem;
    @BindView(R.id.image_myy_dj)
    ImageView imageMyyDj;
    @BindView(R.id.lin_myy)
    LinearLayout linMyy;
    List<Fragment> list = new ArrayList<>();
    List<LinearLayout> llist = new ArrayList<>();
    @BindView(R.id.lay_one)
    LinearLayout layOne;
    @BindView(R.id.lay_two)
    LinearLayout layTwo;
    @BindView(R.id.lay_swe)
    LinearLayout laySwe;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    @Override
    protected int initLayout() {
        return R.layout.activity_principal;
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
        list.add(new YMovieFragment());
        list.add(new ShapeFragment());
        list.add(new MyFragment());
        llist.add(linMovei);
        llist.add(linCinem);
        llist.add(linMyy);
        viewPager.setScanScroll(false);
        ViewAdapter viewAdapter = new ViewAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(viewAdapter);
        imageMoveiDj.setOnClickListener(this);
        imageCinemDj.setOnClickListener(this);
        imageMyyDj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_movei_dj:

                imageMoveiDj.setVisibility(View.GONE);
                imageCinemDj.setVisibility(View.VISIBLE);
                imageMyyDj.setVisibility(View.VISIBLE);
                linMovei.setVisibility(View.VISIBLE);
                linCinem.setVisibility(View.GONE);
                linMyy.setVisibility(View.GONE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.image_cinem_dj:
                imageMoveiDj.setVisibility(View.VISIBLE);
                imageCinemDj.setVisibility(View.GONE);
                imageMyyDj.setVisibility(View.VISIBLE);
                linMovei.setVisibility(View.GONE);
                linCinem.setVisibility(View.VISIBLE);
                linMyy.setVisibility(View.GONE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.image_myy_dj:
                imageMoveiDj.setVisibility(View.VISIBLE);
                imageCinemDj.setVisibility(View.VISIBLE);
                imageMyyDj.setVisibility(View.GONE);
                linMovei.setVisibility(View.GONE);
                linCinem.setVisibility(View.GONE);
                linMyy.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
