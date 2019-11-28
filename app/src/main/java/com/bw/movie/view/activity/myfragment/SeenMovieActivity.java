package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.bean.SeenMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.SeenAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeenMovieActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.rect_list_view)
    RecyclerView rectListView;
    @BindView(R.id.linear_view)
    LinearLayout linearView;

    @Override
    protected int initLayout() {
        return R.layout.activity_seen_movie;
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
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Persenter persenter=new Persenter(this);
        persenter.doSeenMovie(map);

    }
    @Override
    public void onLogCurress(Object obj) {
        SeenMovieBean seenMovieBean= (SeenMovieBean) obj;
        List<SeenMovieBean.ResultBean> result = seenMovieBean.result;
        if (result.size()!=0) {
            rectListView.setVisibility(View.VISIBLE);
            linearView.setVisibility(View.GONE);
            SeenAdapter seenAdapter=new SeenAdapter(result);
            rectListView.setLayoutManager(new LinearLayoutManager(this));
            rectListView.setAdapter(seenAdapter);
            seenAdapter.setDoView(new SeenAdapter.DoView() {
                @Override
                public void onCurress(int id) {
                    Intent intent=new Intent("com.bawei.RevieMovie");
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }else{
            rectListView.setVisibility(View.GONE);
            linearView.setVisibility(View.VISIBLE);
        }

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

    }

    @Override
    public void onLogExurr(String str) {

    }

}
