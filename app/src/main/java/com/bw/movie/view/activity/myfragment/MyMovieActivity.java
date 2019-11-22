package com.bw.movie.view.activity.myfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import com.bw.movie.R;
import com.bw.movie.modle.bean.ExchangeCodeBean;
import com.bw.movie.modle.bean.MyMovieBean;
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
    SimpleDraweeView simpView;
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

        simpView.setImageURI(exchangeCode);
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
//                Bitmap obmp = ((BitmapDrawable) (simpView).getDrawable()).getBitmap();
//                int width = obmp.getWidth();
//                int height = obmp.getHeight();
//                int[] data = new int[width * height];
//                obmp.getPixels(data, 0, width, 0, 0, width, height);
//                RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
//                BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
//                QRCodeReader reader = new QRCodeReader();
//                Result re = null;
//                try {
//                    re = reader.decode(bitmap1);
//                } catch (NotFoundException e) {
//                    e.printStackTrace();
//                } catch (ChecksumException e) {
//                    e.printStackTrace();
//                } catch (FormatException e) {
//                    e.printStackTrace();
//                }
//                if (re == null) {
//                    showAlert(obmp);
//                } else {
//                    showSelectAlert(obmp, re.getText());
//                }
                return false;
            }
        });
    }
    private void showAlert(final Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("保存图片")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterfacem, int i) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterfacem, int i) {
                    }
                });
        builder.show();
    }

    private void showSelectAlert(final Bitmap bitmap, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        String str[] = {"保存图片", "扫二维码"};
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfacem, int i) {
                switch (i) {
                    case 0: {

                    }
                    break;
                    case 1: {
                        Toast.makeText(MyMovieActivity.this, url, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfacem, int i) {

            }
        });
        builder.show();
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
