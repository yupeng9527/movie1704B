package com.bw.movie.view.fragment.particulars;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.CommentBean;
import com.bw.movie.modle.bean.RecommendBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CinAdapter;
import com.bw.movie.view.adapter.CinemaEvaluesAdapter;
import com.bw.movie.view.adapter.CommitAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;

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
public class FilmReviewFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.rlink_view)
    RecyclerView rlinkView;
    Unbinder unbinder;
    private Map<String, Object> map;

    @Override
    protected int initLayout() {
        return R.layout.item_filmreview;
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
        SharedPreferences qq = getContext().getSharedPreferences("qq", App.MODE_PRIVATE);
        String sessionId = qq.getString("sessionId", null);
        int userId = qq.getInt("userId", 0);
        int movieId = qq.getInt("movieId", 0);
        map = new HashMap<>();
        Map<String, Object> smap = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        smap.put("movieId", movieId);
        smap.put("page", 1);
        smap.put("count", 10);
        Persenter persenter = new Persenter(this);
        persenter.doCommit(map, smap);
    }

    @Override
    public void onLogCurress(Object obj) {
        CommentBean commentBean= (CommentBean) obj;
        List<CommentBean.ResultBean> result = commentBean.result;
        final CinAdapter cinAdapter=new CinAdapter(result);
        rlinkView.setLayoutManager(new LinearLayoutManager(getContext()));
        rlinkView.setAdapter(cinAdapter);
        cinAdapter.setDoView(new CinAdapter.DoView() {
            @Override
            public void onCurress(int id) {
                Map<String, Object> smap = new HashMap<>();
                smap.put("commentId", id);
                Persenter persenter=new Persenter(FilmReviewFragment.this);
                persenter.doGreat(map,smap);
                cinAdapter.notifyDataSetChanged();
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
    public void onLogExurr(String str) {
        Log.i("qqq", "onLogExurr: "+str);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
