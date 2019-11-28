package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.base.IBaseVIew;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.Base64;
import com.bw.movie.view.mi.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_pas)
    EditText editPas;
    @BindView(R.id.edit_yzm)
    EditText editYzm;
    @BindView(R.id.but_yzm)
    Button butYzm;
    @BindView(R.id.text_dl)
    TextView textDl;
    @BindView(R.id.but_log)
    Button butLog;
    private Persenter persenter;


    @Override
    protected int initLayout() {
        return R.layout.activity_login;
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
        persenter = new Persenter((IBaseVIew) LoginActivity.this);
    }

    @OnClick({R.id.but_yzm, R.id.text_dl, R.id.but_log})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_yzm:
                String email = editEmail.getText().toString();
                Map<String,String> cmap=new HashMap<>();
                cmap.put("email", email);
                persenter.doCode(cmap);
                break;
            case R.id.but_log:
                String pas = editPas.getText().toString();
                String emil = editEmail.getText().toString();
                String name = editName.getText().toString();
                String yzm = editYzm.getText().toString();
                String decrypt = EncryptUtil.encrypt(pas);
                Map<String,String> map=new HashMap<>();
                map.put("nickName", name);
                map.put("pwd", decrypt);
                map.put("email", emil);
                map.put("code", yzm);
                persenter.doRegister(map);
                finish();
                break;
            case R.id.text_dl:
                Intent intent=new Intent(LoginActivity.this,GuideActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public void onLogCurress(Object obj) {
        RegisterBean registerBean= (RegisterBean) obj;
        if (registerBean.status.equals("0000")) {
            Toast.makeText(this, registerBean.message, Toast.LENGTH_SHORT).show();
            String emil = editEmail.getText().toString();
            String pas = editPas.getText().toString();
//            加密
            String decrypt = EncryptUtil.encrypt(pas);
            Intent intent=new Intent(LoginActivity.this,GuideActivity.class);
            intent.putExtra("email",emil);
            intent.putExtra("pwd",decrypt);
            startActivity(intent);
        }else {
            Toast.makeText(this, registerBean.message, Toast.LENGTH_SHORT).show();
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
    public void onMovieCinema(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


}
