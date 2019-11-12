package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.utils.DateUtil;
import com.bw.movie.modle.version.DateListener;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.activity.MainActivity;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_my_imag)
    ImageView textMyImag;
    @BindView(R.id.text_my_name)
    TextView textMyName;
    @BindView(R.id.text_my_xing)
    TextView textMyXing;
    @BindView(R.id.text_my_data)
    TextView textMyData;
    @BindView(R.id.text_my_email)
    TextView textMyEmail;
    private String s;

    @Override
    protected int initLayout() {
        return R.layout.activity_my;
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
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String email = sp.getString("email", "");
        String pwd = sp.getString("pwd", "");
        String encrypt = EncryptUtil.encrypt(pwd);
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("pwd", encrypt);
        Persenter persenter = new Persenter(this);
        persenter.doGuild(map);
    }

    @Override
    public void onLogCurress(Object obj) {
        final GuideBean guideBean = (GuideBean) obj;
        GuideBean.ResultBean.UserInfoBean userInfo = guideBean.result.userInfo;
        Glide.with(this)
                .load(userInfo.headPic)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.circleCropTransform())
                .into(textMyImag);
        textMyName.setText(userInfo.nickName);
        textMyEmail.setText(userInfo.email);
        int sex = userInfo.sex;
        if (sex == 1) {
            textMyXing.setText("男");
        } else if (sex == 2) {
            textMyXing.setText("女");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tida = format.format(userInfo.lastLoginTime);
        textMyData.setText(tida);
        textMyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.setYearDate(MyActivity.this, new DateListener() {
                    @Override
                    public void setYear(String year) {

                    }

                    @Override
                    public void setMonth(String month) {

                    }

                    @Override
                    public void setDay(String day) {

                    }

                    @Override
                    public void setMouthDate(String year, String month) {

                    }

                    @Override
                    public void setYearDate(String year, String month, String day) {
                        s = year + "-" + month + "-" + day;
                        Log.i("qq", "setYearDate: "+ s);
                        GuideBean.ResultBean result = guideBean.result;
                        Map<String,Object> map=new HashMap<>();
                        map.put("userId",result.userId);
                        map.put("sessionId",result.sessionId);
                        Map<String,String> smap=new HashMap<>();
                        smap.put("birthday", s);
                        Persenter persenter=new Persenter(MyActivity.this);
                        persenter.doBirthday(map,smap);
                    }
                });
            }
        });







    }

    @Override
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {
        textMyData.setText(s);
    }

    @Override
    public void onBannerCurress(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }
}
