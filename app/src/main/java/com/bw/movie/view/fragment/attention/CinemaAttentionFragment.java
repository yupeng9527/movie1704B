package com.bw.movie.view.fragment.attention;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.modle.bean.OnCimenBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinemaAdapter;
import com.bw.movie.view.adapter.attention.CinemaAttentionAdapter;
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
public class CinemaAttentionFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.rlink_view)
    RecyclerView rlinkView;
    @BindView(R.id.linear_view)
    LinearLayout linearView;
    Unbinder unbinder;

    @Override
    protected int initLayout() {
        return R.layout.layout_fragment_cinema;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter= (Persenter) basePersenter;
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
        omap.put("page", 1);
        omap.put("count", 20);
        Persenter persenter=new Persenter(this);
        persenter.doCinemaList(map,omap);
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
        OnCimenBean onCimenBean= (OnCimenBean) obj;
        if (onCimenBean.result==null) {
            linearView.setVisibility(View.VISIBLE);
        }else{
            List<OnCimenBean.ResultBean> result = onCimenBean.result;
            CinemaAttentionAdapter cinemaAdapter=new CinemaAttentionAdapter(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            rlinkView.setLayoutManager(linearLayoutManager);
            rlinkView.setAdapter(cinemaAdapter);
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
