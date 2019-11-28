package com.bw.movie.view.fragment.particular;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaCommentBean;
import com.bw.movie.modle.bean.CinemaInfoBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinemaEvaluesAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.fragment.HotShowFragment;
import com.bw.movie.view.fragment.particulars.FilmReviewFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
public class CinemaEvaluateFragment extends BaseFragment implements IViewContract.doView {

    Unbinder unbinder;
    @BindView(R.id.xlist_view)
    XRecyclerView xlistView;
    int page = 1;
    private Persenter persenter;
    ArrayList<CinemaCommentBean.ResultBean> list=new ArrayList<>();
    private Map<String, Object> omap;
    private Map<String, Object> map;
    private int id;

    @Override
    protected int initLayout() {
        return R.layout.item_cinemaevaluate;
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
        SharedPreferences sp = getContext().getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        id = sp.getInt("id", 0);
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        persenter = new Persenter(this);
        omap = new HashMap<>();
        return rootView;
    }

    @Override
    protected void initData() {
        list.clear();
        xlistView.setLoadingMoreEnabled(true);
        xlistView.setPullRefreshEnabled(true);
        xlistView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                omap.put("page",page);
                omap.put("cinemaId",id);
                omap.put("count",7);
                persenter.onCinemaComment(map,omap);
                xlistView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                omap.put("page",page);
                omap.put("cinemaId",id);
                omap.put("count",7);
                persenter.onCinemaComment(map,omap);
                xlistView.loadMoreComplete();
            }
        });
        omap.put("page",page);
        omap.put("cinemaId",id);
        omap.put("count",7);
        persenter.onCinemaComment(map,omap);
        xlistView.refreshComplete();
    }

    @Override
    public void onLogCurress(Object obj) {
        CinemaCommentBean cinemaCommentBean= (CinemaCommentBean) obj;
        List<CinemaCommentBean.ResultBean> result = cinemaCommentBean.result;
        list.addAll(result);
        final CinemaEvaluesAdapter cinemaEvaluesAdapter=new CinemaEvaluesAdapter(list);
        xlistView.setLayoutManager(new LinearLayoutManager(getContext()));
        xlistView.setAdapter(cinemaEvaluesAdapter);
        cinemaEvaluesAdapter.setDoView(new CinemaEvaluesAdapter.DoView() {
            @Override
            public void onCurress(int id) {
                Map<String, Object> smap = new HashMap<>();
                smap.put("commentId", id);
                Persenter persenter=new Persenter(CinemaEvaluateFragment.this);
                persenter.doGreat(map,smap);
                cinemaEvaluesAdapter.notifyDataSetChanged();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
