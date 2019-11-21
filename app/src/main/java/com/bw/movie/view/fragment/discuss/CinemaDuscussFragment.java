package com.bw.movie.view.fragment.discuss;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaDiscussBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.discuss.CinemaDiscussAdapter;
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
 * date:2019/11/1
 * author:贺少伟(盗)
 * function:
 */
public class CinemaDuscussFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.rlink_view)
    RecyclerView rlinkView;
    @BindView(R.id.linear_view)
    LinearLayout linearView;
    Unbinder unbinder;
    @BindView(R.id.text_name)
    TextView textName;

    @Override
    protected int initLayout() {
        return R.layout.layout_fragment_cinema;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Map<String, Object> omap = new HashMap<>();
        omap.put("longitude", 0);
        omap.put("latitude", 0);
        omap.put("page", 1);
        omap.put("count", 20);
        Persenter persenter = new Persenter(this);
        persenter.onCinemaDiscussList(map, omap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onLogCurress(Object obj) {
        CinemaDiscussBean cinemaDiscussBean = (CinemaDiscussBean) obj;
        if (cinemaDiscussBean.result == null) {
            textName.setText("无影院评论");
            linearView.setVisibility(View.VISIBLE);
        } else {
            List<CinemaDiscussBean.ResultBean> result = cinemaDiscussBean.result;
            CinemaDiscussAdapter cinemaDiscussAdapter=new CinemaDiscussAdapter(result);
            rlinkView.setLayoutManager(new LinearLayoutManager(getContext()));
            rlinkView.setAdapter(cinemaDiscussAdapter);
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
