package com.bw.movie.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_pingf)
    TextView textPingf;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.edit_txt)
    EditText editTxt;
    @BindView(R.id.but_tj)
    Button butTj;
    private SharedPreferences sp;
    private int userId;
    private int movieId;
    private String sessionId;

    @Override
    protected int initLayout() {
        return R.layout.activity_reviews;
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
        sp = getSharedPreferences("qq", Context.MODE_PRIVATE);
        SharedPreferences feil = getSharedPreferences("feil", Context.MODE_PRIVATE);
        int userId = feil.getInt("userId", 0);
        String sessionId = feil.getString("sessionId", null);

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> smap = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        smap.put("movieId", movieId);
        Persenter persenter = new Persenter(this);
        persenter.doDetail(map, smap);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onLogCurress(Object obj) {
        DetilBean detilBean = (DetilBean) obj;
        final DetilBean.ResultBean result = detilBean.result;
butTj.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name = result.name;
        textName.setText(name);
        float rating = ratingBar.getRating();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> smap = new HashMap<>();
        SharedPreferences feil = getSharedPreferences("feil", Context.MODE_PRIVATE);
        int userId = feil.getInt("userId", 0);
        String sessionId = feil.getString("sessionId", null);
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        smap.put("movieId", result.movieId);
        smap.put("commentContent", editTxt.getText().toString());
        smap.put("score", rating);
        Persenter persenter = new Persenter(ReviewsActivity.this);
        persenter.doMoviComm(map,smap);
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
        int i= (int) obj;
        if (i==0){
            finish();
        }
    }

    @Override
    public void onLogExurr(String str) {

    }
}
