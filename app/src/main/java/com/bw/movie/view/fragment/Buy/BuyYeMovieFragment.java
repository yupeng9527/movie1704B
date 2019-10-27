package com.bw.movie.view.fragment.Buy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.TICketBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.buy.BuyMoierAdapter;
import com.bw.movie.view.adapter.buy.BuyyiMoierAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class BuyYeMovieFragment extends BaseFragment implements IViewContract.doView {


    Unbinder unbinder;
    @BindView(R.id.rel_list_view)
    RecyclerView relListView;

    @Override
    protected int initLayout() {
        return R.layout.layout_buy_yi_movie;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", null);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Map<String, Object> qmap = new HashMap<>();
        qmap.put("page", 1);
        qmap.put("count", 10);
        qmap.put("status", 2);
        Persenter persenter = new Persenter(BuyYeMovieFragment.this);
        persenter.doTicter(map, qmap);

    }

    @Override
    public void onLogCurress(Object obj) {
        TICketBean tiCketBean = (TICketBean) obj;
        List<TICketBean.ResultBean> result = tiCketBean.result;
        BuyyiMoierAdapter buyMoierAdapter=new BuyyiMoierAdapter(result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        relListView.setLayoutManager(linearLayoutManager);
        relListView.setAdapter(buyMoierAdapter);
        buyMoierAdapter.setOnBuyyi(new BuyyiMoierAdapter.OnBuyyi() {
            @Override
            public void onCurress(String str, String imag) {
                Intent intent=new Intent("com.bawei.record");
                intent.putExtra("orderId",str);
                intent.putExtra("imageUrl",imag);
                startActivity(intent);
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

    }

    @Override
    public void onLogExurr(String str) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
