package com.bw.movie.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bw.movie.modle.ap.App;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

/**
 * date:2019/10/19
 * author:贺少伟(盗)
 * function:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//        int code = baseResp.errCode;
//        App.api.handleIntent(getIntent(), this);
//        App.api.handleIntent(getIntent(), this);
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                //获取accesstoken
                EventBus.getDefault().post(code);
                break;
            //用户拒绝授权
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();
                break;
            //用户取消
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();
                break;
            default:
                finish();
                break;
        }
    }
}
