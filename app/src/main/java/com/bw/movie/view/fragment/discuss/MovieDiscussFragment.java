package com.bw.movie.view.fragment.discuss;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.modle.bean.MovieDiscussBean;
import com.bw.movie.modle.bean.OnMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.attention.MovieAttentionAdapter;
import com.bw.movie.view.adapter.discuss.MovieDiscussAdapter;
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
 * date:2019/11/1
 * author:贺少伟(盗)
 * function:
 */
public class MovieDiscussFragment extends BaseFragment implements IViewContract.doView {
    @BindView(R.id.rlink_view)
    RecyclerView rlinkView;
    Unbinder unbinder;

    @Override
    protected int initLayout() {
        return R.layout.layout_fragment_movie;
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
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        Map<String, Object> omap = new HashMap<>();
        omap.put("page", 1);
        omap.put("count", 20);
        Persenter persenter = new Persenter(this);
        persenter.onCommentList(map,omap);
    }

    @Override
    public void onLogCurress(Object obj) {
        MovieDiscussBean movieDiscussBean= (MovieDiscussBean) obj;
        List<MovieDiscussBean.ResultBean> result = movieDiscussBean.result;
        MovieDiscussAdapter movieAdapter=new MovieDiscussAdapter(result);
        movieAdapter.notifyDataSetChanged();
        rlinkView.setLayoutManager(new LinearLayoutManager(getContext()));
        rlinkView.setAdapter(movieAdapter);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
