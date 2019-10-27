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

    //    //声明AMapLocationClient类对象
//    public AMapLocationClient mLocationClient = null;
//    //声明AMapLocationClientOption对象
//    public AMapLocationClientOption mLocationOption = null;
//    //声明定位回调监听器
//    public AMapLocationListener mLocationListener = new AMapLocationListener() {
//        @Override
//        public void onLocationChanged(AMapLocation aMapLocation) {
//            if (aMapLocation != null) {
//                if (aMapLocation.getErrorCode() == 0) {
////可在其中解析amapLocation获取相应内容。
//                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    aMapLocation.getLatitude();//获取纬度
//                    aMapLocation.getLongitude();//获取经度
//                    aMapLocation.getAccuracy();//获取精度信息
//                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    aMapLocation.getCountry();//国家信息
//                    aMapLocation.getProvince();//省信息
//                    //城市信息
//                    city = aMapLocation.getCity();
//                    aMapLocation.getDistrict();//城区信息
//                    aMapLocation.getStreet();//街道信息
//                    aMapLocation.getStreetNum();//街道门牌号信息
//                    aMapLocation.getCityCode();//城市编码
//                    aMapLocation.getAdCode();//地区编码
//                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
//                    aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
//                    aMapLocation.getFloor();//获取当前室内定位的楼层
//                    aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
//                    textMapaName.setText(city);
//                }else {
//                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                    Log.e("AmapError","location Error, ErrCode:"
//                            + aMapLocation.getErrorCode() + ", errInfo:"
//                            + aMapLocation.getErrorInfo());
//                }
//            }
//
//        }
//    };
//    private String city;


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
//                //初始化定位
//                mLocationClient = new AMapLocationClient(getContext());
//                //设置定位回调监听
//                mLocationClient.setLocationListener(mLocationListener);
//                //初始化AMapLocationClientOption对象
//                mLocationOption = new AMapLocationClientOption();
//                AMapLocationClientOption option = new AMapLocationClientOption();
//                /**
//                 * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
//                 */
//                option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//                AMapLocationClient locationClient = null;
//                if(null != locationClient){
//                    locationClient.setLocationOption(option);
//                    //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//                    locationClient.stopLocation();
//                    locationClient.startLocation();
//                }
//                //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//
////获取一次定位结果：
////该方法默认为false。
//                mLocationOption.setOnceLocation(true);
//
////获取最近3s内精度最高的一次定位结果：
////设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//                mLocationOption.setOnceLocationLatest(true);
//                //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//                mLocationOption.setInterval(1000);
//                //设置是否返回地址信息（默认返回地址信息）
//                mLocationOption.setNeedAddress(true);
//                //设置是否允许模拟位置,默认为true，允许模拟位置
//                mLocationOption.setMockEnable(true);
//                //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
//                mLocationOption.setHttpTimeOut(20000);
//                //关闭缓存机制
//                mLocationOption.setLocationCacheEnable(false);
//                //给定位客户端对象设置定位参数
//                mLocationClient.setLocationOption(mLocationOption);
//                //mLocationClient.stopLocation();
//                // 停止定位后，本地定位服务并不会被销毁v
//                //启动定位
//                mLocationClient.startLocation();
                getLocation(getContext());


            }
        });
        textMapaName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    //得到地理位置方法
    private synchronized void getLocation(Context context){
        Log.e("TAG","正在执行获取getLocation方法");
        //声明AMapLocationClient类对象
        AMapLocationClient mLocationClient = null;
        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();
        //设置为高精度
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置默认返回地址
        locationClientOption.setNeedAddress(true);
        //设置是否只定位一次
        locationClientOption.setOnceLocation(true);
        if(locationClientOption.isOnceLocation()){
            locationClientOption.setOnceLocationLatest(true);
        }
        //设置是否强制刷新WiFi
        locationClientOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        locationClientOption.setMockEnable(true);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationClientOption.setInterval(2000);
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //为定位进行设置
        mLocationClient.setLocationOption(locationClientOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //启动定位
        mLocationClient.startLocation();

    }
    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    String s = MapUtils.getLocationStr(amapLocation);
                    onIntersen.onFinis(s);
                    textMapaName.setText(s);
                }
            }
        }

    };
  public void setOnIntersen(OnIntersen onIntersen) {
        this.onIntersen = onIntersen;
    }

    private OnIntersen onIntersen;

    public interface OnIntersen {
        void onFinis(String str);

        void onSs(String str);
    }

}
