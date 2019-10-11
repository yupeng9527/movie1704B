package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.liner_rext)
    LinearLayout linerRext;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
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
        final Intent intent = new Intent(this, GuideActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(task, 3000);

    }
}
