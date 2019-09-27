package com.bw.movie.modle.ap;

import android.app.Application;
import android.content.Context;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function: 上下文
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
