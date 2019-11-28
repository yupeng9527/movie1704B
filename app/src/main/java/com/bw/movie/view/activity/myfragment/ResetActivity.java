package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.edit_old_password)
    EditText editOldPassword;
    @BindView(R.id.edit_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_again_new_password)
    EditText editAgainNewPassword;
    @BindView(R.id.but_affirm)
    Button butAffirm;

    @Override
    protected int initLayout() {
        return R.layout.activity_reset;
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
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        butAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldpass = editOldPassword.getText().toString();
                String newpass = editNewPassword.getText().toString();
                String againpass = editAgainNewPassword.getText().toString();
                if (oldpass==null){
                    Toast.makeText(ResetActivity.this, "请输入旧密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newpass==null){
                    Toast.makeText(ResetActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (againpass==null){
                    Toast.makeText(ResetActivity.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
                String sessionId = sp.getString("sessionId", "");
                int userId = sp.getInt("userId", 0);
                String oldencrypt = EncryptUtil.encrypt(oldpass);
                String newencrypt = EncryptUtil.encrypt(newpass);
                String againencrypt = EncryptUtil.encrypt(againpass);
                Map<String,Object> map=new HashMap<>();
                map.put("userId",userId);
                map.put("sessionId",sessionId);
                Map<String,String> omap=new HashMap<>();
                omap.put("oldPwd",oldencrypt);
                omap.put("newPwd",newencrypt);
                omap.put("newPwd2",againencrypt);
                Persenter persenter=new Persenter(ResetActivity.this);
                persenter.doUserPwd(map,omap);
            }
        });

    }

    @Override
    public void onLogCurress(Object obj) {
        finish();
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
}
