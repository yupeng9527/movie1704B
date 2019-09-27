package com.bw.movie.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;

public class MainActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePersenter initPersenter() {
        return null;
    }
}
