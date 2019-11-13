package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasseyActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.edit_emil)
    EditText editEmil;
    @BindView(R.id.edit_old_pass)
    EditText editOldPass;
    @BindView(R.id.edit_new_pass)
    EditText editNewPass;
    @BindView(R.id.edit_qr_pass)
    EditText editQrPass;
    @BindView(R.id.but_dl)
    Button butDl;
    @BindView(R.id.details_back)
    ImageView detailsBack;
    private SharedPreferences sp;
    private String qrpas;

    @Override
    protected int initLayout() {
        return R.layout.activity_passey;
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
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String email = sp.getString("email", "");
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", null);
        editEmil.setText(email);
        final Map<String, String> imap = new HashMap<>();
        final Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);

        butDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newpass = editNewPass.getText().toString();
                final String oldpass = editOldPass.getText().toString();
                final String qrpass = editQrPass.getText().toString();
                if (!newpass.equals(qrpass)) {
                    Toast.makeText(App.context, "密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    String oldpa = EncryptUtil.encrypt(oldpass);
                    String decrypt = EncryptUtil.encrypt(newpass);
                    qrpas = EncryptUtil.encrypt(qrpass);
                    imap.put("oldPwd", oldpa);
                    imap.put("newPwd", decrypt);
                    imap.put("newPwd2", qrpas);
                    Persenter persenter = new Persenter(PasseyActivity.this);
                    persenter.doUserPwd(map, imap);
                }
            }
        });


    }

    @Override
    public void onLogCurress(Object obj) {
        sp.edit().putString("pwd", qrpas)
                .commit();
        Intent intent = new Intent("com.bawei.Prin");
        startActivity(intent);
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
