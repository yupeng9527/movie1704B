package com.bw.movie.modle.utils;

import android.app.Activity;
import android.text.format.Time;

import com.bw.movie.R;
import com.bw.movie.modle.version.DateListener;

import cn.qqtheme.framework.picker.DatePicker;

/**
 * date:2019/11/12
 * author:贺少伟(盗)
 * function:
 */
public class DateUtil {
    public static void setYearDate(final Activity mContext, final DateListener dateListener){
        DatePicker picker = new DatePicker(mContext);
        //获取当前时间
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int monthDay = time.monthDay;
        //设置时间区间
        picker.setRange(2000,2020);
        //设置默认显示时间
        picker.setSelectedItem(year,month+1,monthDay);
        //加入动画
        picker.setAnimationStyle(R.style.Animation_CustomPopup);
        //回传数据
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                dateListener.setYearDate(year,month,day);
            }
        });
        picker.show();
    }


}
