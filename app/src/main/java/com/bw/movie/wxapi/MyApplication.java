package com.bw.movie.wxapi;

import android.app.Application;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class MyApplication extends Application {

    /**
     *     IWXAPI 是第三方app和微信通信的openApi接口
     */
    private static IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        regToWx();
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this,Constants.WX_APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(Constants.WX_APP_ID);
    }

    public static IWXAPI getWXApi(){
        return api;
    }
}
