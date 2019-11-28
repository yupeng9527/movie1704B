package com.bw.movie.view.activity.myfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.ExchangeCodeBean;
import com.bw.movie.modle.bean.MyMovieBean;
import com.bw.movie.modle.utils.ImageUtils;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.activity.MainActivity;
import com.bw.movie.view.adapter.myfragment.MyMovieAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMovieActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.re_list_view)
    RecyclerView reListView;
    @BindView(R.id.rel_list)
    RelativeLayout relList;
    @BindView(R.id.simp_view)
    ImageView simpView;
    @BindView(R.id.but_ton)
    Button butTon;
    @BindView(R.id.lin_layout)
    LinearLayout linLayout;
    private Map<String, Object> map;

    @Override
    protected int initLayout() {
        return R.layout.activity_my_movie;
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
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Log.i("qqq", "onCreate: "+sessionId+"   "+userId);
        Persenter persenter = new Persenter(this);
        persenter.doMyBean(map);
    }

    @Override
    public void onLogCurress(Object obj) {
        MyMovieBean myMovieBean = (MyMovieBean) obj;
        final List<MyMovieBean.ResultBean> result = myMovieBean.result;
        MyMovieAdapter myMovieAdapter = new MyMovieAdapter(result);
        reListView.setLayoutManager(new LinearLayoutManager(this));
        reListView.setAdapter(myMovieAdapter);
        myMovieAdapter.setIview(new MyMovieAdapter.onMovie() {
            @Override
            public void onCurr(int i) {
                Persenter persenter = new Persenter(MyMovieActivity.this);
                persenter.doExchangeCode(map, i);
            }
        });
    }

    @Override
    public void onShapeCurress(Object obj) {
        ExchangeCodeBean exchangeCodeBean = (ExchangeCodeBean) obj;
        ExchangeCodeBean.ResultBean result = exchangeCodeBean.result;
        String exchangeCode = result.exchangeCode;

//        simpView.setImageURI(exchangeCode);
        Glide.with(this)
                .load(exchangeCode)
                .into(simpView);
        linLayout.setVisibility(View.VISIBLE);
        butTon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayout.setVisibility(View.GONE);
            }
        });
        simpView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ImageView imageView = (ImageView) v;
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable ();
                Bitmap bitmap = drawable.getBitmap ();
                File file = new File ( getCacheDir (), "acd.jpg" );
                try {
                    bitmap.compress ( Bitmap.CompressFormat.JPEG,100,new FileOutputStream( file ) );
                    CodeUtils.analyzeBitmap ( file.getAbsolutePath (), new CodeUtils.AnalyzeCallback () {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {

                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText ( App.context, "解析失败", Toast.LENGTH_SHORT ).show ();
                        }
                    } );
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }
                return true;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        if (requestCode==210){
            if (data==null){
                return;
            }
            Bundle bundle = data.getExtras ();
            if (bundle.getInt ( CodeUtils.RESULT_TYPE )==CodeUtils.RESULT_SUCCESS){
                String string = bundle.getString ( CodeUtils.RESULT_STRING );
                Toast.makeText ( this,string, Toast.LENGTH_SHORT ).show ();
            }else {
                Toast.makeText ( this, "解析失败！", Toast.LENGTH_SHORT ).show ();
            }
        }

        if (requestCode==220){
            if (data!=null){
                Uri uri = data.getData ();
                try {
                    CodeUtils.analyzeBitmap ( ImageUtils.getImageAbsolutePath ( this,uri ), new CodeUtils.AnalyzeCallback () {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText ( App.context, result , Toast.LENGTH_SHORT ).show ();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText ( App.context, "解析失败", Toast.LENGTH_SHORT ).show ();
                        }
                    } );
                } catch (Exception e) {
                    e.printStackTrace ();
                }
            }
        }
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
