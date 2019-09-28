package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

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
        final String email = intent.getStringExtra("email");
        String pwd = intent.getStringExtra("pwd");
        String decrypt = EncryptUtil.decrypt(pwd);
        editEmil.setText(email);
        editPass.setText(decrypt);

        textZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
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
    }

    @Override
    public void onLogCurress(Object obj) {
        GuideBean guideBean= (GuideBean) obj;
        if ("0000".equals(guideBean.status)){
            Toast.makeText(this, guideBean.message, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(GuideActivity.this,ShowActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, guideBean.message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onLogExurr(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
