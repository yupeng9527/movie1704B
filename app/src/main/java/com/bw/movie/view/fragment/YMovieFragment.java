package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.gaode.MapUtils;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.BannerBean;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.HotAdapter;
import com.bw.movie.view.adapter.SoonMovieAdapter;
import com.bw.movie.view.adapter.YMovieAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.SearchView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class YMovieFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.ban_ner)
    Banner banNer;
    @BindView(R.id.but_text_not)
    TextView butTextNot;
    @BindView(R.id.but_text_show)
    TextView butTextShow;
    @BindView(R.id.but_text_not_movie)
    TextView butTextNotMovie;
    Unbinder unbinder;
    int page = 1;
    @BindView(R.id.xlist_not)
    RecyclerView xlistNot;
    @BindView(R.id.xlist_show)
    RecyclerView xlistShow;
    @BindView(R.id.xlist_movie)
    RecyclerView xlistMovie;
    @BindView(R.id.imag_view)
    ImageView imagView;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_score)
    TextView textScore;
    @BindView(R.id.bit_gaopiao)
    Button bitGaopiao;
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> smap = new HashMap<>();
    @BindView(R.id.sear_ch)
    SearchView searCh;

    @Override
    protected int initLayout() {
        return R.layout.item_fragment_prin;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        butTextNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.sh");
                startActivity(intent);
            }
        });
        butTextNotMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.sh");
                startActivity(intent);
            }
        });
        butTextShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.sh");
                startActivity(intent);
            }
        });
        searCh.setOnIntersen(new SearchView.OnIntersen() {
            @Override
            public void onFinis(String str) {
                Toast.makeText(App.context, str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSs(String str) {

            }
        });
        Persenter persenter = new Persenter(YMovieFragment.this);
        persenter.doMovieList(page);
        persenter.SoonMovieList(map, page);
        persenter.HotMovieList(page);
        persenter.DoBanner();

    }


    @Override
    public void onLogCurress(Object obj) {
        List<MoVieListBean.ResultBean> resultBeanList = (List<MoVieListBean.ResultBean>) obj;

        YMovieAdapter yMovieAdapter = new YMovieAdapter(resultBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        xlistNot.setLayoutManager(linearLayoutManager);
        xlistNot.setAdapter(yMovieAdapter);


    }

    @Override
    public void onShapeCurress(Object obj) {
        List<SoonMovieBean.ResultBean> resultBeans = (List<SoonMovieBean.ResultBean>) obj;
        final SoonMovieAdapter soonMovieAdapter = new SoonMovieAdapter(resultBeans);
        xlistShow.setLayoutManager(new LinearLayoutManager(getContext()));
        xlistShow.setAdapter(soonMovieAdapter);
        soonMovieAdapter.notifyDataSetChanged();
        soonMovieAdapter.setIview(new SoonMovieAdapter.Iview() {
            @Override
            public void onCurr(int i) {
                smap.put("movieId", i);
                Persenter persenter = new Persenter(YMovieFragment.this);
                persenter.doResert(map, smap);

            }
        });
    }

    @Override
    public void onMyCurress(Object obj) {
        List<HotBean.ResultBean> result = (List<HotBean.ResultBean>) obj;
        Glide.with(this)
                .load(result.get(0).horizontalImage)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(imagView);
        textName.setText(result.get(0).name);
        textScore.setText(result.get(0).score + "分");
        HotAdapter hotAdapter = new HotAdapter(result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        xlistMovie.setLayoutManager(linearLayoutManager);
        xlistMovie.setAdapter(hotAdapter);
    }

    @Override
    public void onBannerCurress(Object obj) {
        List<BannerBean.ResultBean> resultBeans = (List<BannerBean.ResultBean>) obj;
        List<String> list = new ArrayList<>();
        banNer.setDelayTime(3000);
        for (int i = 0; i < resultBeans.size(); i++) {
            list.add(resultBeans.get(i).imageUrl);
        }
        banNer.setImages(list)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load(path)
                                .error(R.mipmap.ic_launcher)
                                .placeholder(R.mipmap.ic_launcher_round)
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .into(imageView);
                    }
                })
                .isAutoPlay(true)
                .setBannerAnimation(Transformer.DepthPage)
                .start();
    }

    @Override
    public void onLogExurr(String str) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


}
