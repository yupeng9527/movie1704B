package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.SysMsgListBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.SysMsgListAdapter;
import com.bw.movie.view.adapter.attention.CinemaAttentionAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SysMsgListActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.rexy_list_view)
    RecyclerView rexyListView;
    private Persenter persenter;
    private Map<String, Object> map;
    private SysMsgListAdapter sysMsgListAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_sys_msg_list;
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
        map = new HashMap<>();
        map.put("userId",userId);
        map.put("sessionId",sessionId);
        Map<String,Object> omap=new HashMap<>();
        omap.put("page",1);
        omap.put("count",10);
        persenter = new Persenter(this);
        persenter.doSysMsgList(map,omap);
    }
    @Override
    public void onLogCurress(Object obj) {
        SysMsgListBean sysMsgListBean= (SysMsgListBean) obj;
        List<SysMsgListBean.ResultBean> result = sysMsgListBean.result;
        sysMsgListAdapter = new SysMsgListAdapter(result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rexyListView.setLayoutManager(linearLayoutManager);
        rexyListView.setAdapter(sysMsgListAdapter);
        sysMsgListAdapter.setDoData(new SysMsgListAdapter.DoData() {
            @Override
            public void doLogID(int id) {
                persenter.doMsgStatus(map,id);
            }
        });
    }

    @Override
    public void onShapeCurress(Object obj) {
        sysMsgListAdapter.notifyDataSetChanged();
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
