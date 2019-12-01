package com.bw.movie.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.SchedBean;
import com.bw.movie.modle.bean.SeatleBean;
import com.bw.movie.modle.bean.TicketsBean;
import com.bw.movie.modle.bean.VerifyBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.MovieSeatAdapter;
import com.bw.movie.view.adapter.RommSeatAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class RommActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.room_back)
    ImageView roomBack;
    @BindView(R.id.room_name)
    TextView roomName;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.room_VideoPlayer)
    JCVideoPlayer roomVideoPlayer;
    @BindView(R.id.room_movieSeat)
    RecyclerView roomMovieSeat;
    @BindView(R.id.real)
    RelativeLayout real;
    @BindView(R.id.room_time)
    TextView roomTime;
    @BindView(R.id.room_recycler)
    RecyclerView roomRecycler;
    @BindView(R.id.btn_purchaseOrder)
    Button btnPurchaseOrder;
    @BindView(R.id.room_btn)
    Button roomBtn;
    @BindView(R.id.radio_zzfb)
    RadioButton radioZzfb;

    @BindView(R.id.liner_lay)
    LinearLayout linerLay;
    @BindView(R.id.radio_wx)
    RadioButton radioWx;
    @BindView(R.id.imag_gb)
    ImageView imagGb;
    private String string;
    private long sum;
    private double fare;
    private double zf;
    private CheckBox wxzf;
    private String orderId;
    private int cinemaId;
    private String str;
    private MovieSeatAdapter seatAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_romm;
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
        roomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences qq = getSharedPreferences("qq_m", Context.MODE_PRIVATE);
        final String name = qq.getString("name", null);
//        final String imageUrl = qq.getString("imageUrl", null);
//        int movieid = qq.getInt("movieid", 0);
        SharedPreferences sp = getSharedPreferences("hallId", Context.MODE_PRIVATE);
        final int hallId = sp.getInt("hallId", 0);
        roomName.setText(name);
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("movieId", 0);
        cinemaId = intent.getIntExtra("cinemaId", 0);
        String photo = intent.getStringExtra("photo");
//        fare = intent.getDoubleExtra("price",0);
        String imageUrl = intent.getStringExtra("imageUrl");
        final Map<String, Object> map = new HashMap<>();
        map.put("cinemaId", cinemaId);
        map.put("movieId", movieId);
        Persenter persenter = new Persenter(RommActivity.this);
        persenter.doSchedule(map);
        roomVideoPlayer.setUp(photo, null);
        Glide.with(this)
                .load(imageUrl)
                .into(roomVideoPlayer.ivThumb);

        btnPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerLay.setVisibility(View.VISIBLE);
            }
        });


        imagGb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               linerLay.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLogCurress(Object obj) {
        SeatleBean seatleBean = (SeatleBean) obj;
        final List<SeatleBean.ResultBean> result = seatleBean.result;
        LinearLayoutManager manager = new GridLayoutManager(this, 8);
        roomMovieSeat.setLayoutManager(manager);
        seatAdapter = new MovieSeatAdapter(result);
        roomMovieSeat.setAdapter(seatAdapter);
        seatAdapter.setCallBack(new MovieSeatAdapter.CallBack() {
            @Override
            public void getBack(String s) {
                Toast.makeText(RommActivity.this, s, Toast.LENGTH_SHORT).show();
                string = s;
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).status == 3) {
                        sum++;
                    }
                }
                if (sum > 4) {
                    Toast.makeText(RommActivity.this, "最多" + sum + "张", Toast.LENGTH_SHORT).show();

                } else {
                    //设置价格
                    if (sum != 0) {
                        zf = sum * fare;
                        btnPurchaseOrder.setText("￥:" + sum * fare);
                        btnPurchaseOrder.setVisibility(View.VISIBLE);
                        roomBtn.setVisibility(View.INVISIBLE);
                        sum = 0;
                    } else if (sum == 0) {
                        roomBtn.setVisibility(View.VISIBLE);
                        btnPurchaseOrder.setVisibility(View.INVISIBLE);
                    }
                }

            }

            @Override
            public void getStrng(List<String> list) {
                str = "";
                for (int i = 0; i < list.size(); i++) {
                    if (str.length()==0){
                        str +=list.get(i);
                    }else{
                        str +=","+list.get(i);
                    }
                }
                Log.i("qqq", "getStrng: "+ str);
                SharedPreferences qq = getSharedPreferences("zw", Context.MODE_PRIVATE);
                qq.edit().putString("seat", str).commit();
            }
        });
    }

    @Override
    public void onShapeCurress(Object obj) {
        SchedBean schedBean = (SchedBean) obj;
        List<SchedBean.ResultBean> result = schedBean.result;
        roomTime.setText("选择影厅和时间("+result.size()+")");
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        roomRecycler.setLayoutManager(manager);
        RommSeatAdapter adapter = new RommSeatAdapter(result);
        roomRecycler.setAdapter(adapter);
        adapter.setCallBack(new RommSeatAdapter.iCallBack() {



            @Override
            public void getBack(String s) {
                Persenter persenter = new Persenter(RommActivity.this);
                persenter.doSeatle(cinemaId);
            }
            @Override
            public void getPrice(Double price) {
                fare=price;
            }
            @Override
            public void getId(final int idd) {
//                SharedPreferences qq = getSharedPreferences("zw", Context.MODE_PRIVATE);
//                qq.edit().putInt("scheduleId",idd).commit();
                SharedPreferences fp = getSharedPreferences("feil", Context.MODE_PRIVATE);
                final String sessionId = fp.getString("sessionId", null);
                final int userId = fp.getInt("userId", 0);
                String s = userId + "" + idd + "movie";
                Log.i("qqq", "onClick: " + s);
                final String sign = MD5(s);
                Log.i("qqq", "onClick: " + sign);
                radioWx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//        订单
                        SharedPreferences qw = getSharedPreferences("zw", Context.MODE_PRIVATE);
                        String seat = qw.getString("seat", null);
//                        String scheduleId = qw.getString("scheduleId", null);
                        Map<String, Object> ma = new HashMap<>();
                        ma.put("userId", userId);
                        ma.put("sessionId", sessionId);
                        Map<String, Object> oma = new HashMap<>();
                        oma.put("scheduleId", idd);
                        oma.put("seat", seat);
                        oma.put("sign", sign);
                        if (radioWx.isChecked()) {
                            Persenter persenter1 = new Persenter(RommActivity.this);
                            persenter1.doTickets(ma, oma);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onMyCurress(Object obj) {
        TicketsBean ticketsBean= (TicketsBean) obj;
        SharedPreferences fp = getSharedPreferences("feil", Context.MODE_PRIVATE);

        String sessionId = fp.getString("sessionId", null);
        int userId = fp.getInt("userId", 0);
        final Map<String,Object> ma=new HashMap<>();
        ma.put("userId", userId);
        ma.put("sessionId", sessionId);
        final Map<String,Object> lmap=new HashMap<>();
        lmap.put("payType",1);
        lmap.put("orderId",ticketsBean.orderId);
        AlertDialog.Builder builder = new AlertDialog.Builder(RommActivity.this);
        builder.setMessage("是否选择跳转购买");
        builder.setTitle("支付金额:￥"+zf);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Persenter persenter=new Persenter(RommActivity.this);
                persenter.doPay(ma,lmap);
            }
        });
       builder.setNegativeButton("取消",null);
       builder.show();

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
        payReq.extData = "app data";
        App.api.sendReq(payReq);
        seatAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMovieCinema(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }

    //MD5加密
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
