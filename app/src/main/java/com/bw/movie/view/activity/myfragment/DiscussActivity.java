package com.bw.movie.view.activity.myfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscussActivity extends BaseActivity {

    @BindView(R.id.details_back)
    ImageView detailsBack;

    @Override
    protected int initLayout() {
        return R.layout.activity_discuss;
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
    }
}
