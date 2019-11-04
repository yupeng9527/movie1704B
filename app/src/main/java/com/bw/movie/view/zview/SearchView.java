package com.bw.movie.view.zview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.R;
import com.bw.movie.gaode.MapUtils;
import com.bw.movie.modle.ap.App;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class SearchView extends RelativeLayout {


    @BindView(R.id.imag_mapa)
    ImageView imagMapa;
    @BindView(R.id.text_mapa_name)
    TextView textMapaName;
    @BindView(R.id.imag_search)
    ImageView imagSearch;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    AMapLocationListener mLocationListener = null;
    public SearchView(Context context) {
        super(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context)
                .inflate(R.layout.item_view, this, true);
        ButterKnife.bind(inflate);
        imagMapa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//初始化定位
                mLocationClient = new AMapLocationClient(getContext());
//设置定位回调监听

                mLocationClient.setLocationListener(mLocationListener);
//启动定位
                mLocationClient.startLocation();
                //异步获取定位结果
                AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation amapLocation) {
                        if (amapLocation != null) {
                            if (amapLocation.getErrorCode() == 0) {
                                //解析定位结果
                                textMapaName.setText(amapLocation.getAddress());
                            }
                        }
                    }

                };
            }
        });


    }


//    public AMapLocationClient mLocationClient = null;
//    //声明定位回调监听器
//    public AMapLocationListener mLocationListener = new AMapLocationListener() {
//        @Override
//        public void onLocationChanged(AMapLocation aMapLocation) {
//            Log.d("Location", "mLocationListener>>>>: ");
//            if (aMapLocation != null) {
//                if (aMapLocation.getErrorCode() == 0) {
//                    //可在其中解析amapLocation获取相应内容。
//                    aMapLocation.getAddress();
//                    Log.d("Location", "aMapLocation.getAddress(): " + aMapLocation.getAddress()+"坐标"+aMapLocation.getLatitude());
//                    //tv_location.setText(aMapLocation.getAddress());
//                    textMapaName.setText(aMapLocation.getAddress());
//                } else {
//                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                    Log.e("AmapError", "location Error, ErrCode:"
//                            + aMapLocation.getErrorCode() + ", errInfo:"
//                            + aMapLocation.getErrorInfo());
//                }
//            }
//        }
//    };
//    //声明AMapLocationClientOption对象
//    public AMapLocationClientOption mLocationOption = null;
//
//    public void getLocation() {
//        //初始化定位
//        AMapLocationClient mLocationClient = new AMapLocationClient(getContext());
//        //设置定位回调监听
//        mLocationClient.setLocationListener(mLocationListener);
//        //初始化AMapLocationClientOption对象
//        mLocationOption = new AMapLocationClientOption();
//        /**
//         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
//         */
//        //不允许模拟定位
//        mLocationOption.setMockEnable(false);
//        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//        if (null != mLocationClient) {
//            mLocationClient.setLocationOption(mLocationOption);
//            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//            mLocationClient.stopLocation();
//            mLocationClient.startLocation();
//        }
//        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        //启动定位
//        mLocationClient.startLocation();
////        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
////        mLocationClient.onDestroy();
//    }

  public void setOnIntersen(OnIntersen onIntersen) {
        this.onIntersen = onIntersen;
    }

    private OnIntersen onIntersen;

    public interface OnIntersen {
        void onFinis(String str);

        void onSs(String str);
    }

}
