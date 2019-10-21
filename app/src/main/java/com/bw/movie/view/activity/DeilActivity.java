package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.FragAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.fragment.particulars.FilmReviewFragment;
import com.bw.movie.view.fragment.particulars.ForeshowFragment;
import com.bw.movie.view.fragment.particulars.IntroduceFragment;
import com.bw.movie.view.fragment.particulars.StagePhotoFragment;
import com.bw.movie.view.zview.CustomViewPager;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeilActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.details_img)
    ImageView detailsImg;
    @BindView(R.id.details_mark)
    TextView detailsMark;
    @BindView(R.id.details_critic)
    TextView detailsCritic;
    @BindView(R.id.details_name)
    TextView detailsName;
    @BindView(R.id.details_type)
    TextView detailsType;
    @BindView(R.id.details_long)
    TextView detailsLong;
    @BindView(R.id.details_btn_img)
    ImageView detailsBtnImg;
    @BindView(R.id.details_time)
    TextView detailsTime;
    @BindView(R.id.details_content)
    TextView detailsContent;
    @BindView(R.id.vvv)
    RelativeLayout vvv;
    @BindView(R.id.details_tab)
    TabLayout detailsTab;

    @BindView(R.id.btn_reviews)
    Button btnReviews;
    @BindView(R.id.btn_seat)
    Button btnSeat;
    List<String> slist = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> smap = new HashMap<>();
    @BindView(R.id.details_btn_img_false)
    ImageView detailsBtnImgFalse;
    @BindView(R.id.details_content_false)
    TextView detailsContentFalse;
    @BindView(R.id.details_page)
    CustomViewPager detailsPage;
    private int movieId;

    @Override
    protected int initLayout() {
        return R.layout.activity_deil;
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

        initTab();
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", null);
        Intent intent = getIntent();
        int movieId = intent.getExtras().getInt("movieId");
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        smap.put("movieId", movieId);
        Persenter persenter = new Persenter(this);
        persenter.doDetail(map, smap);
        SharedPreferences pp = getSharedPreferences("qq", Context.MODE_PRIVATE);
        pp.edit()
//                .putString("")
                .putString("sessionId",sessionId)
                .putInt("userId",userId)
                .putInt("movieId",movieId)
                .commit();
        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DeilActivity.this, ReviewsActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void initTab() {
        list.add(new IntroduceFragment());
        list.add(new ForeshowFragment());
        list.add(new StagePhotoFragment());
        list.add(new FilmReviewFragment());
        slist.add("介绍");
        slist.add("预告");
        slist.add("剧照");
        slist.add("影评");
        for (int i = 0; i < slist.size(); i++) {
            String title = slist.get(i);
            TabLayout.Tab tab = detailsTab.newTab();
            if (tab != null) {
                tab.setText(title);
                detailsTab.addTab(tab);
            }
        }
        detailsPage.setScanScroll(false);
        detailsTab.setupWithViewPager(detailsPage);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), list, slist);
        fragAdapter.notifyDataSetChanged();
        detailsPage.setAdapter(fragAdapter);
    }

    @Override
    public void onLogCurress(Object obj) {

        DetilBean detilBean = (DetilBean) obj;
        final DetilBean.ResultBean result = detilBean.result;
        Glide.with(this)
                .load(result.imageUrl)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(detailsImg);
        detailsMark.setText("评分:   " + result.score);
        detailsCritic.setText("评论:   " + result.commentNum);
        detailsName.setText(result.name);
        detailsType.setText(result.movieType);
        detailsLong.setText(result.duration);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(result.releaseTime);
        detailsTime.setText(format + "   中国大陆上映");
        final Persenter persenter = new Persenter(this);

        if (result.whetherFollow == 1) {
            detailsBtnImg.setVisibility(View.GONE);
            detailsContent.setVisibility(View.GONE);
            detailsContentFalse.setVisibility(View.VISIBLE);
            detailsBtnImgFalse.setVisibility(View.VISIBLE);
            detailsBtnImgFalse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    persenter.doCancel(map, smap);
                    persenter.doDetail(map, smap);
                    detailsBtnImg.setVisibility(View.VISIBLE);
                    detailsContent.setVisibility(View.VISIBLE);
                    detailsContentFalse.setVisibility(View.GONE);
                    detailsBtnImgFalse.setVisibility(View.GONE);
                }
            });
        } else if (result.whetherFollow == 2) {
            detailsBtnImg.setVisibility(View.VISIBLE);
            detailsContent.setVisibility(View.VISIBLE);
            detailsContentFalse.setVisibility(View.GONE);
            detailsBtnImgFalse.setVisibility(View.GONE);

            detailsBtnImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    persenter.doFollow(map, smap);
                    persenter.doDetail(map, smap);
                    detailsBtnImg.setVisibility(View.GONE);
                    detailsContent.setVisibility(View.GONE);
                    detailsContentFalse.setVisibility(View.VISIBLE);
                    detailsBtnImgFalse.setVisibility(View.VISIBLE);

                }
            });
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

    }


}
