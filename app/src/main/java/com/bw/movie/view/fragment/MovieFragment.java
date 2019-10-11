package com.bw.movie.view.fragment;

import android.support.v4.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class MovieFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.layout_movie;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter= (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {

    }
}
