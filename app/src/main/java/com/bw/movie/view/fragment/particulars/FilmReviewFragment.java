package com.bw.movie.view.fragment.particulars;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.zview.LazyLoadFragment;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class FilmReviewFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.item_filmreview;
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
