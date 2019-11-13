package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.HeadPicBean;
import com.bw.movie.modle.utils.DateUtil;
import com.bw.movie.modle.version.DateListener;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_my_imag)
    SimpleDraweeView textMyImag;
    @BindView(R.id.text_my_name)
    TextView textMyName;
    @BindView(R.id.text_my_xing)
    TextView textMyXing;
    @BindView(R.id.text_my_data)
    TextView textMyData;
    @BindView(R.id.text_my_email)
    TextView textMyEmail;
    @BindView(R.id.but_shoot)
    Button butShoot;
    @BindView(R.id.but_photo)
    Button butPhoto;
    @BindView(R.id.linear_gone)
    LinearLayout linearGone;
    @BindView(R.id.text_my_phone)
    TextView textMyPhone;
    @BindView(R.id.liner_phone)
    LinearLayout linerPhone;
    @BindView(R.id.edit_text_edit)
    EditText editTextEdit;
    @BindView(R.id.but_versi)
    Button butVersion;
    @BindView(R.id.liner_phone_hl)
    LinearLayout linerPhoneHl;

    private String s;
    //定义一个私有的变量来拍照回调
    private String path;
    //定义静态变量来记录相册
    private static final int IMAGE = 1;
    private Map<String, Object> map;
    private String email;
    private String phone;

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
        email = sp.getString("email", "");
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
        textMyImag.setImageURI(userInfo.headPic);
        textMyName.setText(userInfo.nickName);
        textMyEmail.setText(email);
        textMyPhone.setText(userInfo.phone);

        int sex = userInfo.sex;
        if (sex == 1) {
            textMyXing.setText("男");
        } else if (sex == 2) {
            textMyXing.setText("女");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String tida = format.format(userInfo.birthday);
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
                        Log.i("qq", "setYearDate: " + s);
                        GuideBean.ResultBean result = guideBean.result;
                        map = new HashMap<>();
                        map.put("userId", result.userId);
                        map.put("sessionId", result.sessionId);
                        Map<String, String> smap = new HashMap<>();
                        smap.put("birthday", s);
                        Persenter persenter = new Persenter(MyActivity.this);
                        persenter.doBirthday(map, smap);
                    }
                });
            }
        });
        textMyImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGone.setVisibility(View.VISIBLE);
            }
        });
        linerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerPhoneHl.setVisibility(View.VISIBLE);
                linerPhone.setVisibility(View.GONE);
            }
        });
        butVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> omap = new HashMap<>();
                omap.put("userId", guideBean.result.userId);
                omap.put("sessionId", guideBean.result.sessionId);
                phone = editTextEdit.getText().toString();
                Map<String, String> smap = new HashMap<>();
                smap.put("phone", phone);
                Persenter persenter = new Persenter(MyActivity.this);
                persenter.doUserPhone(omap, smap);
            }
        });
    }


    @Override
    public void onShapeCurress(Object obj) {
        HeadPicBean headPicBean = (HeadPicBean) obj;

    }

    @Override
    public void onMyCurress(Object obj) {
        textMyData.setText(s);
    }

    @Override
    public void onBannerCurress(Object obj) {
        linerPhoneHl.setVisibility(View.GONE);
        linerPhone.setVisibility(View.VISIBLE);
        textMyPhone.setText(phone);
    }

    @Override
    public void onLogExurr(String str) {

    }
}
