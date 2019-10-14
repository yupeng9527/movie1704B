package com.bw.movie.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * date:2019/9/29
 * author:贺少伟(盗)
 * function:
 */
public abstract class BaseFragment extends Fragment {
    protected BasePersenter basePersenter;
    private View inflate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (initLayout()!=0) {
            inflate = inflater.inflate(initLayout(), container, false);
        }
        return inflate;
    }
    protected abstract int initLayout();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (basePersenter!=null) {
            basePersenter=initPersenter();
        }
        initData();
    }



    protected abstract BasePersenter initPersenter();
    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (basePersenter!=null) {
            basePersenter.onDestroy();
        }

    }
}
