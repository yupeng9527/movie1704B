package com.bw.movie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.ScheduleListBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.ScheduleListAdapter;
import com.bw.movie.view.base.BaseFragment;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/11/20
 * author:贺少伟(盗)
 * function:
 */
public class ScheduleTabFragment extends BaseFragment implements IViewContract.doView {

    @BindView(R.id.xlist_view)
    XRecyclerView xlistView;
    Unbinder unbinder;
    int page = 1;
    ArrayList<ScheduleListBean.ResultBean> list = new ArrayList<>();
    private Persenter persenter;
    private Map<String, Object> map;
    private ScheduleListAdapter scheduleListAdapter;
    private int did;


    @Override
    protected int initLayout() {
        return R.layout.layout_schedule_tab;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();

        did = bundle.getInt("did");
        map = new HashMap<>();
        persenter=new Persenter(this);
        list.clear();

//        xlistView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                page = 1;
//                map.put("cinemaId", did);
//                map.put("page", page);
//                map.put("count", 10);
//                persenter.onScheduleList(map);
//                list.clear();
//                xlistView.refreshComplete();
//            }
//
//            @Override
//            public void onLoadMore() {
//                page++;
//                map.put("cinemaId", did);
//                map.put("page", page);
//                map.put("count", 10);
//                persenter.onScheduleList(map);
//                xlistView.loadMoreComplete();
//            }
//        });
        map.put("cinemaId", did);
        map.put("page", page);
        map.put("count", 15);
        persenter.onScheduleList(map);
        xlistView.setLoadingMoreEnabled(false);
        xlistView.setPullRefreshEnabled(false);
//        scheduleListAdapter.notifyDataSetChanged();
        return rootView;
    }
    @Override
    protected void initData() {

    }




    @Override
    public void onLogCurress(Object obj) {

    }


    @Override
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {
        ScheduleListBean scheduleListBean = (ScheduleListBean) obj;
        List<ScheduleListBean.ResultBean> result = scheduleListBean.result;
//            list.addAll(result);
        if(result.size()!=0){
            scheduleListAdapter = new ScheduleListAdapter(result,getContext());
            xlistView.setLayoutManager(new LinearLayoutManager(getContext()));
            xlistView.setAdapter(scheduleListAdapter);
            scheduleListAdapter.setAreaView(new ScheduleListAdapter.AreaView() {
                @Override
                public void onCurress(int id, String ponth, String imag) {
                    Intent intent1 = new Intent("com.bawei.Romm");
                    Double price=0.1;
                    intent1.putExtra("movieId", id);
                    intent1.putExtra("price", price);
                    intent1.putExtra("cinemaId", did);
                    intent1.putExtra("photo", ponth);
                    intent1.putExtra("imageUrl", imag);
                    startActivity(intent1);
                }
            });
        }else{
            Toast.makeText(App.context, "该影院无播放电影", Toast.LENGTH_SHORT).show();
        }
            
//            scheduleListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBannerCurress(Object obj) {

    }

    @Override
    public void onMovieCinema(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {
        Log.i("qq", "onLogExurr: "+str);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
