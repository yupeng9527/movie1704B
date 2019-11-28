package com.bw.movie.view.fragment.particular;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaInfoBean;
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
public class CinemaDetailsFragment extends BaseFragment implements IViewContract.doView {

    Unbinder unbinder;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.text_lu)
    TextView textLu;

    @Override
    protected int initLayout() {
        return R.layout.item_cinemadetails;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        int id = sp.getInt("id", 0);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Persenter persenter = new Persenter(this);
        persenter.doCinemaInfo(map, id);
        return rootView;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onLogCurress(Object obj) {
        CinemaInfoBean cinemaInfoBean = (CinemaInfoBean) obj;
        CinemaInfoBean.ResultBean result = cinemaInfoBean.result;
        textPhone.setText(result.phone);
        textAddress.setText(result.address);
        textLu.setText(result.vehicleRoute);
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
    public void onMovieCinema(Object obj) {

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
