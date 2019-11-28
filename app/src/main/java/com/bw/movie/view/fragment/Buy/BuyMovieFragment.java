package com.bw.movie.view.fragment.Buy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.TICketBean;
import com.bw.movie.modle.bean.VerifyBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.buy.BuyMoierAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.tencent.mm.opensdk.modelpay.PayReq;

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
public class BuyMovieFragment extends BaseFragment implements IViewContract.doView {


    Unbinder unbinder;
    @BindView(R.id.rel_list_view)
    RecyclerView relListView;
    @BindView(R.id.imag_gb)
    ImageView imagGb;
    @BindView(R.id.radio_wx)
    RadioButton radioWx;
    @BindView(R.id.radio_zzfb)
    RadioButton radioZzfb;
    @BindView(R.id.btn_purchaseOrder)
    Button btnPurchaseOrder;
    @BindView(R.id.liner_lay)
    LinearLayout linerLay;

    @Override
    protected int initLayout() {
        return R.layout.layout_buy_movie;
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
        qmap.put("status", 1);
        Persenter persenter = new Persenter(BuyMovieFragment.this);
        persenter.doTicter(map, qmap);
        imagGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerLay.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLogCurress(Object obj) {
        TICketBean tiCketBean = (TICketBean) obj;
        final List<TICketBean.ResultBean> result = tiCketBean.result;

        BuyMoierAdapter buyMoierAdapter = new BuyMoierAdapter(result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        relListView.setLayoutManager(linearLayoutManager);
        relListView.setAdapter(buyMoierAdapter);
        buyMoierAdapter.setOnBuy(new BuyMoierAdapter.OnBuy() {
            @Override
            public void onCurress(final int i) {
                linerLay.setVisibility(View.VISIBLE);
                SharedPreferences fp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
                String sessionId = fp.getString("sessionId", null);
                int userId = fp.getInt("userId", 0);
                final Map<String,Object> ma=new HashMap<>();
                ma.put("userId", userId);
                ma.put("sessionId", sessionId);

                btnPurchaseOrder.setText(""+0.0+"元");
                radioWx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (radioWx.isChecked()){
                            radioZzfb.setChecked(false);
                            btnPurchaseOrder.setText("微信支付"+result.get(i).price+"元");
                            final Map<String,Object> lmap=new HashMap<>();
                            lmap.put("payType",1);
                            lmap.put("orderId",result.get(i).orderId);
                            btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (radioWx.isChecked()) {
                                        intDaBuy(ma,lmap);
                                    }
                                }
                            });
                        }else{
                            btnPurchaseOrder.setText(""+0.0+"元");
                        }
                    }
                });
                radioZzfb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (radioZzfb.isChecked()){
                            radioWx.setChecked(false);
                            btnPurchaseOrder.setText("支付宝支付"+result.get(i).price+"元");
                            final Map<String,Object> lmap=new HashMap<>();
                            lmap.put("payType",2);
                            lmap.put("orderId",result.get(i).orderId);
                            btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (radioZzfb.isChecked()) {
//                                intDaBuy(ma,lmap);
                                        Toast.makeText(App.context, "暂时未开发", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            btnPurchaseOrder.setText(""+0.0+"元");
                        }
                    }
                });



            }
        });
    }

    private void intDaBuy(Map<String, Object> ma, Map<String, Object> lmap) {
        Persenter persenter=new Persenter(this);
        persenter.doPay(ma,lmap);
    }

    @Override
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {

    }

    @Override
    public void onBannerCurress(Object obj) {
        VerifyBean verifyBean= (VerifyBean) obj;
        PayReq payReq = new PayReq();
        payReq.appId =verifyBean.appId;
        payReq.partnerId = verifyBean.partnerId;
        payReq.prepayId = verifyBean.prepayId;
        payReq.nonceStr = verifyBean.nonceStr;
        payReq.timeStamp = verifyBean.timeStamp;
        payReq.packageValue = verifyBean.packageValue;
        payReq.sign = verifyBean.sign;
        payReq.extData = "app data"; // optional
        App.api.sendReq(payReq);
    }

    @Override
    public void onMovieCinema(Object obj) {

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
