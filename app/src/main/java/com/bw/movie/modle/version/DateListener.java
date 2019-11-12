package com.bw.movie.modle.version;

/**
 * date:2019/11/12
 * author:贺少伟(盗)
 * function:
 */
public interface DateListener {
    void setYear(String year);

    void setMonth(String month);

    void setDay(String day);

    void setMouthDate(String year, String month);

    void setYearDate(String year, String month, String day);

}
