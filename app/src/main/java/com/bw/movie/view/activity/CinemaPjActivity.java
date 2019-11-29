package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaInfoBean;
import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CinemaPjActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_cinme_name)
    TextView textCinmeName;
    @BindView(R.id.edit_txt_cinme)
    EditText editTxtCinme;
    @BindView(R.id.but_tj)
    Button butTj;
    private Map<String, Object> map;
    private Persenter persenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_movie_key;
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
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        final Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        persenter = new Persenter(this);
        persenter.doCinemaInfo(map, id);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        butTj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editTxtCinme.getText().toString();
                if (s!=null){
                    Map<String,Object> omap=new HashMap<>();
                    omap.put("cinemaId",id);
                    omap.put("commentContent",s);
                    persenter.doCinemaCom(map,omap);
                }else{
                    Toast.makeText(CinemaPjActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onLogCurress(Object obj) {
        CinemaInfoBean cinemaInfoBean = (CinemaInfoBean) obj;
        CinemaInfoBean.ResultBean result = cinemaInfoBean.result;
        textCinmeName.setText(result.name);

    }

    @Override
    public void onShapeCurress(Object obj) {
        CodeBean codeBean= (CodeBean) obj;
        finish();
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
