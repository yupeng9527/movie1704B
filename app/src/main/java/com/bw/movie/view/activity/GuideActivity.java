package com.bw.movie.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;
import com.bw.movie.wxapi.MyApplication;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.edit_emil)
    EditText editEmil;
    @BindView(R.id.edit_pass)
    EditText editPass;
    @BindView(R.id.but_wjmm)
    Button butWjmm;
    @BindView(R.id.text_zhuce)
    TextView textZhuce;
    @BindView(R.id.but_dl)
    Button butDl;
    @BindView(R.id.imag_view)
    ImageView imagView;
    private static final String APP_ID="wxb3852e6a6b7d9516";
//    private IWXAPI api;
    @Override
    protected int initLayout() {
        return R.layout.activity_guide;
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
        Intent intent = getIntent();

        String email = intent.getStringExtra("email");

        String pwd = intent.getStringExtra("pwd");
        String decrypt = EncryptUtil.decrypt(pwd);
        editEmil.setText(email);
        editPass.setText(decrypt);
        textZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.lo");
                startActivity(intent);
            }
        });
        butDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emil = editEmil.getText().toString();
                String pass = editPass.getText().toString();
                String encrypt = EncryptUtil.encrypt(pass);
                Map<String,String> map=new HashMap<>();
                map.put("email",emil);
                map.put("pwd",encrypt);
                Persenter persenter=new Persenter(GuideActivity.this);
                persenter.doGuild(map);
            }
        });
        butWjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.passey");
                startActivity(intent);
            }
        });
//        微信
        imagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send oauth request
//                SendAuth.Req req = new SendAuth.Req();
//                req.scope = "snsapi_userinfo";
//                req.state = "wechat_sdk_demo_test";
//                App.api.sendReq(req);
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = UUID.randomUUID().toString();
                MyApplication.getWXApi().sendReq(req);
            }
        });

    }



    @Override
    public void onLogCurress(Object obj) {
        GuideBean guideBean= (GuideBean) obj;
        if ("0000".equals(guideBean.status)){
            Toast.makeText(this, guideBean.message, Toast.LENGTH_SHORT).show();
            SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
            String email = editEmil.getText().toString();
            String pwd = editPass.getText().toString();

            sp.edit().putString("email", email)
                    .putString("pwd", pwd)
                    .putString("nickName", guideBean.result.userInfo.nickName)
                    .putString("headPic", guideBean.result.userInfo.headPic)
                    .putInt("weq",1)
                    .putInt("userId",guideBean.result.userId)
                    .putString("sessionId",guideBean.result.sessionId)
                    .commit();
            finish();
        }else{
            Toast.makeText(this, guideBean.message, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
