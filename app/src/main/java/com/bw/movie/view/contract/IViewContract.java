package com.bw.movie.view.contract;

import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.base.IBaseVIew;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function:  契约类
 */
public interface IViewContract {
//    契约回调
    interface doView extends IBaseVIew{}
//      契约persenter
    abstract class doData extends BasePersenter {
    public doData(IBaseVIew iBaseVIew) {
        super(iBaseVIew);
    }


}

}
