package com.bw.movie.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.R;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function: 抽取Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected BasePersenter basePersenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initLayout()!=0) {
            setContentView(initLayout());
            if (basePersenter!=null) {
                basePersenter=initPersenter();
            }
        }else{
            finish();
        }

    }
//  布局文件
    protected abstract int initLayout();
//   回调成功或者失败
    protected abstract BasePersenter initPersenter();


//    内存泄漏

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePersenter!=null) {
            basePersenter.onDestroy();
        }
    }
}
