package com.bw.movie.view.activity.myfragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.UserBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.myfragment.UserMovieAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.re_list_view)
    RecyclerView reListView;

    @Override
    protected int initLayout() {
        return R.layout.activity_user;
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
        setTranslucent(this);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("sessionId",sessionId);
        Persenter persenter=new Persenter(this);
        persenter.doUserBean(map);
    }
    @Override
    public void onLogCurress(Object obj) {
        UserBean userBean= (UserBean) obj;
        List<UserBean.ResultBean> result = userBean.result;
        UserMovieAdapter userMovieAdapter=new UserMovieAdapter(result);
        reListView.setLayoutManager(new LinearLayoutManager(this));
        reListView.setAdapter(userMovieAdapter);
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
