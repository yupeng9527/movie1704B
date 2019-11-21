package com.bw.movie.view.activity.myfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.MyMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.adapter.myfragment.MyMovieAdapter;
import com.bw.movie.view.adapter.myfragment.UserMovieAdapter;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.zview.CustomPopDialog2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMovieActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.re_list_view)
    RecyclerView reListView;
    private Map<String, Object> map;

    @Override
    protected int initLayout() {
        return R.layout.activity_my_movie;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter= (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        setTranslucent(this);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", "");
        int userId = sp.getInt("userId", 0);
        map = new HashMap<>();
        map.put("userId",userId);
        map.put("sessionId",sessionId);
        Persenter persenter=new Persenter(this);
        persenter.doMyBean(map);
    }
    @Override
    public void onLogCurress(Object obj) {
        MyMovieBean myMovieBean= (MyMovieBean) obj;
        final List<MyMovieBean.ResultBean> result = myMovieBean.result;
        MyMovieAdapter myMovieAdapter=new MyMovieAdapter(result);
        reListView.setLayoutManager(new LinearLayoutManager(this));
        reListView.setAdapter(myMovieAdapter);
        myMovieAdapter.setIview(new MyMovieAdapter.onMovie() {
            @Override
            public void onCurr(int i) {
                int i1 = i;

//                Bitmap bitmap = BitmapFactory.decodeFile(result.get(i).cinemaName);// 这里是获取图片Bitmap，也可以传入其他参数到Dialog中
//                CustomPopDialog2.Builder dialogBuild = new CustomPopDialog2.Builder(getBaseContext());
//                dialogBuild.setImage(bitmap);
//                CustomPopDialog2 dialog = dialogBuild.create();
//                dialog.setCanceledOnTouchOutside(true);// 点击外部区域关闭
//                dialog.show();

            }
        });
    }

    @Override
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {

    }

    @Override
    public void onBannerCurress(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }

}
