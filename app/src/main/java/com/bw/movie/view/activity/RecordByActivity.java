package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.RecordByBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordByActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.xq_image_view)
    SimpleDraweeView xqImageView;
    @BindView(R.id.xq_text_name)
    TextView xqTextName;
    @BindView(R.id.xq_re_lay)
    RelativeLayout xqReLay;
    @BindView(R.id.xq_text_cinema)
    TextView xqTextCinema;
    @BindView(R.id.xq_text_screeningHall)
    TextView xqTextScreeningHall;
    @BindView(R.id.xq_text_amount)
    TextView xqTextAmount;
    @BindView(R.id.xq_text_seat)
    TextView xqTextSeat;
    @BindView(R.id.xq_text_beginTime)
    TextView xqTextBeginTime;
    @BindView(R.id.xq_text_orderId)
    TextView xqTextOrderId;
    @BindView(R.id.xq_text_createTime)
    TextView xqTextCreateTime;
    @BindView(R.id.xq_text_price)
    TextView xqTextPrice;
    private String imageUrl;

    @Override
    protected int initLayout() {
        return R.layout.activity_record_by;
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
        SharedPreferences fp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = fp.getString("sessionId", null);
        int userId = fp.getInt("userId", 0);
        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");
        imageUrl = intent.getStringExtra("imageUrl");
        final Map<String,Object> ma=new HashMap<>();
        ma.put("userId", userId);
        ma.put("sessionId", sessionId);
        Map<String,Object> oma=new HashMap<>();
        oma.put("orderId",orderId);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Persenter persenter=new Persenter(this);
        persenter.doRecordBy(ma,oma);

    }
    @Override
    public void onLogCurress(Object obj) {
        RecordByBean recordByBean= (RecordByBean) obj;
        RecordByBean.ResultBean result = recordByBean.result;
        xqImageView.setImageURI(imageUrl);
        xqTextCinema.setText(result.cinemaName);
        xqTextName.setText(result.movieName);
        xqTextScreeningHall.setText(result.screeningHall);
        xqTextAmount.setText(result.amount+"张");
        xqTextSeat.setText(result.seat+"座位");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(result.createTime);
        xqTextCreateTime.setText(format);
        xqTextBeginTime.setText(result.beginTime+"-"+result.endTime);
        xqTextOrderId.setText(result.orderId);
        xqTextPrice.setText("￥"+result.price);
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
