package com.bw.movie.view.fragment.Buy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.modle.bean.TICketBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
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


    @BindView(R.id.rel_list_view)
    RelativeLayout relListView;
    Unbinder unbinder;

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
        SharedPreferences sp = getContext().getSharedPreferences("qaz", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", null);
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("sessionId",sessionId);
        Map<String,Object> qmap=new HashMap<>();
        qmap.put("status",1);
        qmap.put("count",10);
        qmap.put("status",2);
        Persenter persenter = new Persenter(BuyYeMovieFragment.this);
        persenter.doTicter(map,qmap);

    }

    @Override
    public void onLogCurress(Object obj) {
        TICketBean tiCketBean= (TICketBean) obj;
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
