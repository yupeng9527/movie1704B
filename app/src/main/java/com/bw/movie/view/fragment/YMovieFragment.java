package com.bw.movie.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.BannerBean;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinemaXRecycleAdapter;
import com.bw.movie.view.adapter.HotAdapter;
import com.bw.movie.view.adapter.SoonMovieAdapter;
import com.bw.movie.view.adapter.YMovieAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.SearchView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.AbstractList;
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


    Unbinder unbinder;
    int page = 1;

    Map<String, Object> map = new HashMap<>();
    Map<String, Object> smap = new HashMap<>();
//    @BindView(R.id.xlist_view_d)
//    XRecyclerView xlistViewD;

    //    private CinemaXRecycleAdapter cinemaXRecycleAdapter;
    @BindView(R.id.sear_ch)
    SearchView searCh;

    @BindView(R.id.ban_ner)
    XBanner banNer;
    @BindView(R.id.but_text_not)
    TextView butTextNot;
    @BindView(R.id.xlist_not)
    RecyclerView xlistNot;
    @BindView(R.id.but_text_show)
    TextView butTextShow;
    @BindView(R.id.xlist_show)
    RecyclerView xlistShow;
    @BindView(R.id.but_text_not_movie)
    TextView butTextNotMovie;
    @BindView(R.id.imag_view)
    SimpleDraweeView imagView;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_score)
    TextView textScore;
    @BindView(R.id.bit_gaopiao)
    Button bitGaopiao;
    @BindView(R.id.xlist_movie)
    RecyclerView xlistMovie;


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
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        xlistViewD.setLayoutManager(linearLayoutManager);
//        cinemaXRecycleAdapter = new CinemaXRecycleAdapter(getActivity());
//        xlistViewD.setPullRefreshEnabled(false);
//        xlistViewD.setLoadingMoreEnabled(false);
    }


    @Override
    public void onLogCurress(Object obj) {
        List<MoVieListBean.ResultBean> resultBeanList = (List<MoVieListBean.ResultBean>) obj;
//        cinemaXRecycleAdapter.getResultA(resultBeanList);
        YMovieAdapter yMovieAdapter = new YMovieAdapter(resultBeanList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        xlistNot.setLayoutManager(linearLayoutManager);
        xlistNot.setAdapter(yMovieAdapter);
        butTextNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.sh");
                startActivity(intent);
            }
        });
        yMovieAdapter.setAreaView(new YMovieAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                Intent intent1 = new Intent("com.bawei.SelectMovie");
                intent1.putExtra("movieId",id);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onShapeCurress(Object obj) {
        List<SoonMovieBean.ResultBean> resultBeans = (List<SoonMovieBean.ResultBean>) obj;
//        cinemaXRecycleAdapter.getResultB(resultBeans);
        final SoonMovieAdapter soonMovieAdapter = new SoonMovieAdapter(resultBeans,getContext());
       xlistShow.setLayoutManager(new LinearLayoutManager(getContext()));
       xlistShow.setAdapter(soonMovieAdapter);
        soonMovieAdapter.notifyDataSetChanged();
        soonMovieAdapter.setIview(new SoonMovieAdapter.Iview() {
            @Override
            public void onCurr(int i) {
                SharedPreferences sp = getActivity().getSharedPreferences("feil", Context.MODE_PRIVATE);
                String sessionId = sp.getString("sessionId", null);
                int userId = sp.getInt("userId", 0);
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> smap = new HashMap<>();
                map.put("userId", userId);
                map.put("sessionId", sessionId);
                smap.put("movieId", i);
                Persenter persenter = new Persenter(YMovieFragment.this);
                persenter.doResert(map, smap);

            }
        });
        butTextShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.sh");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMyCurress(Object obj) {
        List<HotBean.ResultBean> result = (List<HotBean.ResultBean>) obj;
//        cinemaXRecycleAdapter.getResultC(result);
//        xlistViewD.setAdapter(cinemaXRecycleAdapter);

        HotAdapter hotAdapter = new HotAdapter(result,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        xlistMovie.setLayoutManager(linearLayoutManager);
        xlistMovie.setAdapter(hotAdapter);
        butTextNotMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.sh");
                startActivity(intent);
            }
        });
        hotAdapter.setAreaView(new HotAdapter.AreaView() {
            @Override
            public void onCurress(int id) {
                Intent intent1 = new Intent("com.bawei.SelectMovie");
                intent1.putExtra("movieId",id);
                startActivity(intent1);
            }
        });
        imagView.setImageURI(result.get(0).horizontalImage);
        textName.setText(result.get(0).name);
        textScore.setText(result.get(0).score + "分");
    }

    @Override
    public void onBannerCurress(Object obj) {
        final List<BannerBean.ResultBean> resultBeans = (List<BannerBean.ResultBean>) obj;
//        View inflate = LinearLayout.inflate(getActivity(), R.layout.layout_frces, null);
//        XBanner banNer = inflate.findViewById(R.id.ban_ner);
        banNer.setBannerData(R.layout.image_fresco, new AbstractList<SimpleBannerInfo>() {
            @Override
            public int size() {
                return resultBeans.size();
            }

            @Override
            public SimpleBannerInfo get(int index) {
                return null;
            }
        });
        banNer.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                String imageUrl = resultBeans.get(position).imageUrl;
                SimpleDraweeView simpleDraweeView = view.findViewById(R.id.my_image_view);
                AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                        .setUri(imageUrl)
                        .setAutoPlayAnimations(true)
                        .build();
                simpleDraweeView.setController(build);

            }
        });

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
