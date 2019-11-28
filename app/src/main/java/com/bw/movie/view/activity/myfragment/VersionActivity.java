package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.bean.VersionBean;
import com.bw.movie.modle.utils.APKVersionCodeUtils;
import com.bw.movie.modle.version.ProgressDownloader;
import com.bw.movie.modle.version.ProgressResponseBody;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.activity.MainActivity;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import rx.functions.Action0;

public class VersionActivity extends BaseActivity implements IViewContract.doView, ProgressResponseBody.ProgressListener {

    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.but_version)
    Button butVersion;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.downloadButton)
    Button downloadButton;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.continue_button)
    Button continueButton;
    @BindView(R.id.liner_pre)
    LinearLayout linerPre;
    private long breakPoints;
    private ProgressDownloader downloader;
    private File file;
    private long totalBytes;
    private long contentLength;
    private String downloadUrl;

    @Override
    protected int initLayout() {
        return R.layout.activity_version;
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
        String verName = APKVersionCodeUtils.getVerName(this);
        String code = APKVersionCodeUtils.getVersionCode(this) + "";
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        map.put("ak", code);
        Persenter persenter = new Persenter(this);
        persenter.doVersion(map);
    }

    @Override
    public void onLogCurress(Object obj) {
        final VersionBean versionBean = (VersionBean) obj;
        butVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerPre.setVisibility(View.VISIBLE);
                downloadUrl = versionBean.downloadUrl;
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_buttons(v);
            }
        });
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadButtons(v);
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continue_buttons(v);
            }
        });
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
    public void downloadButtons(View view){

        // 新下载前清空断点信息
        breakPoints = 0L;
        // 下载的位置
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sample.apk");
        downloader = new ProgressDownloader(downloadUrl, file, this);
        downloader.download(0L);
    }
    public void cancel_buttons(View view){
        downloader.pause();
        Toast.makeText(this, "下载暂停", Toast.LENGTH_SHORT).show();
        // 存储此时的totalBytes，即断点位置。
        breakPoints = totalBytes;
    }
    public void continue_buttons(View view){
        downloader.download(breakPoints);
    }
    @Override
    public void onPreExecute(long contentLength) {
        // 文件总长只需记录一次，要注意断点续传后的contentLength只是剩余部分的长度
        if (this.contentLength == 0L) {
            this.contentLength = contentLength;
            progressBar.setMax((int) (contentLength / 1024));
        }
    }

    @Override
    public void update(long totalBytes, boolean done) {
        this.totalBytes = totalBytes + breakPoints;
        progressBar.setProgress((int) (totalBytes + breakPoints) / 1024);
        if (done) {
            // 切换到主线程
            Observable
                    .empty()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Exception {
                            Toast.makeText(VersionActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .subscribe();
        }
    }

}
