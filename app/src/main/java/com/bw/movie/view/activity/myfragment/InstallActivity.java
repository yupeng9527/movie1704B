package com.bw.movie.view.activity.myfragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.version.DataCleanManager;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstallActivity extends BaseActivity {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_cache)
    TextView textCache;
    @BindView(R.id.linear_clear)
    LinearLayout linearClear;
    @BindView(R.id.liner_updata)
    LinearLayout linerUpdata;
    @BindView(R.id.linear_reset)
    LinearLayout linearReset;
    @BindView(R.id.linear_exit)
    LinearLayout linearExit;

    @Override
    protected int initLayout() {
        return R.layout.activity_install;
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
        linerUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.Version");
                startActivity(intent);
            }
        });
        linearClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.clearAllCache(InstallActivity.this);
                Intent intent=new Intent("com.bawei.Install");
                startActivity(intent);
                finish();
            }
        });
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            textCache.setText(totalCacheSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        linearReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.Reset");
                startActivity(intent);
            }
        });
        linearExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.guide");
                startActivity(intent);
                finish();
            }
        });
    }

}
