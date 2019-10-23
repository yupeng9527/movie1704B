package com.bw.movie.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.fragment.MyFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.edit_pl)
    EditText editPl;
    @BindView(R.id.but_tj)
    Button butTj;

    @Override
    protected int initLayout() {
        return R.layout.activity_feed;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter= (Persenter) basePersenter;
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
        butTj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp =getSharedPreferences("feil", Context.MODE_PRIVATE);
                String sessionId = sp.getString("sessionId", null);
                int userId = sp.getInt("userId", 0);
                Map<String,Object> map=new HashMap<>();
                map.put("userId",userId);
                map.put("sessionId",sessionId);
                Map<String,Object> omap=new HashMap<>();
                String s = editPl.getText().toString();
                omap.put("content",s);
                Persenter persenter=new Persenter(FeedActivity.this);
                persenter.doFeedBack(map,omap);
            }
        });



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

            finish();

    }

    @Override
    public void onLogExurr(String str) {

    }
}
