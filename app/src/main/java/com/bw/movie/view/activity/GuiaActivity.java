package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuiaActivity extends BaseActivity {


    @BindView(R.id.ling)
    ImageView ling;

    @Override
    protected int initLayout() {
        return R.layout.activity_guia;
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
        ling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.Prin");
                startActivity(intent);
                finish();
            }
        });
    }



}
