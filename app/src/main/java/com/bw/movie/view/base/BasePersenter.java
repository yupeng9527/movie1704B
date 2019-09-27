package com.bw.movie.view.base;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function:
 */
public class BasePersenter {
    protected IBaseVIew iBaseVIew;

    public BasePersenter(IBaseVIew iBaseVIew) {
        this.iBaseVIew = iBaseVIew;
    }

//    内存泄漏

    public void onDestroy(){
        if (iBaseVIew!=null) {
            iBaseVIew=null;
        }
    }
}
