package com.bw.movie.view.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bw.movie.view.fragment.MyFragment;
import com.bw.movie.view.fragment.ScheduleTabFragment;

import java.util.List;

/**
 * date:2019/11/20
 * author:贺少伟(盗)
 * function:
 */
public class ScheduleTabAdapter extends FragmentPagerAdapter {
    List<String> result;
    int did;

    public ScheduleTabAdapter(FragmentManager fm, List<String> result, int did) {
        super(fm);
        this.result = result;
        this.did = did;
    }


    @Override
    public Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("did",did);
        ScheduleTabFragment scheduleTabFragment = new ScheduleTabFragment();
        scheduleTabFragment.setArguments(bundle);
        return scheduleTabFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return result.get(position);
    }

    @Override
    public int getCount() {
        return result.size();
    }
}
