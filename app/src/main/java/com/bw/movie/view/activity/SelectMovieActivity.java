package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.modle.bean.ByRegionBean;
import com.bw.movie.modle.bean.DateListBean;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.modle.bean.RegionListBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinemaByAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class SelectMovieActivity extends BaseActivity implements IViewContract.doView {

    @BindView(R.id.video_view)
    JCVideoPlayer videoView;
    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_select_name)
    TextView textSelectName;
    @BindView(R.id.text_select_time)
    TextView textSelectTime;
    @BindView(R.id.text_select_score)
    TextView textSelectScore;
    @BindView(R.id.text_select_director)
    TextView textSelectDirector;
    @BindView(R.id.imag_search)
    ImageView imagSearch;
    @BindView(R.id.xlist_select_view)
    XRecyclerView xlistSelectView;
    ArrayAdapter<String> arrayAdapter;
    @BindView(R.id.sp_select_area)
    Spinner spSelectArea;
    @BindView(R.id.sp_select_time)
    Spinner spSelectTime;
    @BindView(R.id.text_price_di)
    TextView textPriceDi;
    private List<String> teamList;
    private int po;
    private String s;
    private int movieId;
    private CinemaByAdapter cinemaByAdapter;
    private String photo;
    private String imageUrl;

    @Override
    protected int initLayout() {
        return R.layout.activity_select_movie;
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
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", null);
        Intent intent = getIntent();
        movieId = intent.getExtras().getInt("movieId");
        final Map<String, Object> map = new HashMap<>();
        Map<String, Object> smap = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        smap.put("movieId", movieId);
        final Persenter persenter = new Persenter(this);
        persenter.doDetail(map, smap);
        persenter.doRegionList();
        persenter.doDateList();
        Map<String, Object> fmap = new HashMap<>();
        fmap.put("movieId", movieId);
        fmap.put("regionId", 3);
        fmap.put("page", 1);
        fmap.put("count", 5);
        persenter.doByRegin(fmap);

        textPriceDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> fmap = new HashMap<>();
                fmap.put("movieId", movieId);
                fmap.put("page", 1);
                fmap.put("count", 5);
                persenter.onPriceBy(fmap);
            }
        });
        imagSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onLogCurress(Object obj) {
        DetilBean detilBean = (DetilBean) obj;
        final DetilBean.ResultBean result = detilBean.result;
        List<DetilBean.ResultBean.ShortFilmListBean> shortFilmList = result.shortFilmList;
        List<DetilBean.ResultBean.MovieDirectorBean> movieDirector1 = result.movieDirector;
        photo = movieDirector1.get(0).photo;
        imageUrl = shortFilmList.get(0).imageUrl;
        videoView.setUp(movieDirector1.get(0).photo, movieDirector1.get(0).name);
        Glide.with(this)
                .load(shortFilmList.get(0).imageUrl)
                .into(videoView.ivThumb);
        textSelectName.setText(result.name);
        textSelectScore.setText(result.score + "分");
        textSelectTime.setText(result.duration);
        List<DetilBean.ResultBean.MovieDirectorBean> movieDirector = result.movieDirector;
        String name = movieDirector.get(0).name;
        textSelectDirector.setText(name + "");


    }

    @Override
    public void onShapeCurress(Object obj) {
        ByRegionBean byRegionBean = (ByRegionBean) obj;
        List<ByRegionBean.ResultBean> result = byRegionBean.result;
        cinemaByAdapter = new CinemaByAdapter(result);
        xlistSelectView.setLayoutManager(new LinearLayoutManager(this));
        xlistSelectView.setAdapter(cinemaByAdapter);
        cinemaByAdapter.setAreaView(new CinemaByAdapter.AreaView() {
            @Override
            public void onCurress(int id, Double price) {
                Intent intent = new Intent("com.bawei.Romm");

                intent.putExtra("movieId", movieId);
                intent.putExtra("price",price);
                intent.putExtra("cinemaId", id);
                intent.putExtra("photo", photo);
                intent.putExtra("imageUrl", imageUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMyCurress(Object obj) {
        RegionListBean regionListBean = (RegionListBean) obj;
        List<RegionListBean.ResultBean> result = regionListBean.result;
        ArrayList<String> stringArrayList = new ArrayList<String>();

        for (int i = 0; i < result.size(); i++) {
            String name1 = result.get(i).regionName;
            stringArrayList.add(name1);
        }
        String[] spinnerItems = stringArrayList.toArray(new String[stringArrayList.size()]);


        //自定义选择填充后的字体样式
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(SelectMovieActivity.this,
                R.layout.item_select, spinnerItems);
        //自定义下拉的字体样式
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        spSelectArea.setAdapter(spinnerAdapter);
        spSelectArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                po = position;
                Map<String, Object> fmap = new HashMap<>();
                fmap.put("movieId", movieId);
                fmap.put("regionId", po);
                fmap.put("page", 1);
                fmap.put("count", 5);
                final Persenter persenter = new Persenter(SelectMovieActivity.this);
                persenter.doByRegin(fmap);
                cinemaByAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onBannerCurress(Object obj) {
        DateListBean dateListBean = (DateListBean) obj;
        final List<String> result = dateListBean.result;
        ArrayList<String> stringArrayList = new ArrayList<String>();

        for (int i = 0; i < result.size(); i++) {
            String name1 = result.get(i);
            stringArrayList.add(name1);
        }
        String[] spinnerItems = stringArrayList.toArray(new String[stringArrayList.size()]);


        //自定义选择填充后的字体样式
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(SelectMovieActivity.this,
                R.layout.item_select, spinnerItems);
        //自定义下拉的字体样式
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        spSelectTime.setAdapter(spinnerAdapter);
        spSelectTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = spinnerAdapter.getItem(position).toString();
                Map<String, Object> dmap = new HashMap<>();
                dmap.put("movieId", movieId);
                dmap.put("date", s);
                dmap.put("page", 1);
                dmap.put("count", 5);
                final Persenter persenter = new Persenter(SelectMovieActivity.this);
                persenter.onByCineamDate(dmap);
                xlistSelectView.setPullRefreshEnabled(false);
                xlistSelectView.setLoadingMoreEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onLogExurr(String str) {

    }
}
