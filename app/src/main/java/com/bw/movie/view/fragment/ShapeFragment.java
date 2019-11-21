package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.CnemaAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.zview.CustomViewPager;
import com.bw.movie.view.zview.LazyLoadFragment;
import com.bw.movie.view.zview.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class ShapeFragment extends LazyLoadFragment {
    @BindView(R.id.sear_chv)
    SearchView searChv;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    Unbinder unbinder;
    List<String> list = new ArrayList<>();
    List<Fragment> flist = new ArrayList<>();
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    @Override
    protected int initLayout() {
        return R.layout.item_fragment_shape;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void initData() {
        flist.add(new RecommendFragment());
        flist.add(new NearByFragment());
        flist.add(new AreaFragment());

        list.add("推荐影院");
        list.add("附近影院");
        list.add("海淀区");

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i);
            TabLayout.Tab tab = tabLayout.newTab();
            if (tab != null) {
                tab.setText(title);
                tabLayout.addTab(tab);
            }
        }
        viewPager.setScanScroll(true);
        tabLayout.setupWithViewPager(viewPager);
        CnemaAdapter cnemaAdapter = new CnemaAdapter(getChildFragmentManager(), flist, list);
        cnemaAdapter.notifyDataSetChanged();
        viewPager.setAdapter(cnemaAdapter);
    }

    @Override
    public void fetchData() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
