package com.bw.movie.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class CnemaAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    List<String> slist;

    public CnemaAdapter(FragmentManager fm, List<Fragment> list, List<String> slist) {
        super(fm);
        this.list = list;
        this.slist = slist;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return slist.get(position%list.size());
    }
}
