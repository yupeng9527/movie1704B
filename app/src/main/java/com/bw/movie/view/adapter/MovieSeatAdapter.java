package com.bw.movie.view.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bw.movie.R;
import com.bw.movie.modle.bean.SeatleBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther:
 * @Date: 2019/10/2 16:16:24
 * @Description:
 */
public class MovieSeatAdapter extends XRecyclerView.Adapter<MovieSeatAdapter.MovieVIewHolder> {

    List<SeatleBean.ResultBean> result ;
    final List<String> list=new ArrayList<>();
    public  MovieSeatAdapter(List<SeatleBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_seat_layout, viewGroup, false);
        return new MovieVIewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVIewHolder movieVIewHolder, final int i) {


        int status = result.get(i).status;
        if (status == 2) {
            movieVIewHolder.cheBox.setChecked(false);
            movieVIewHolder.cheBox.setBackgroundResource(R.drawable.screen_checkboxy);
        }
        movieVIewHolder.cheBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String row1 = result.get(i).row;
                    String seat2 = result.get(i).seat;
                    String s = row1 + "-" + seat2;
                    if (movieVIewHolder.cheBox.isChecked()) {
                        result.get(i).status=3;
                        list.add(s);
                        callBack.getStrng(list);
                        callBack.getBack(result.get(i).row + "排" + result.get(i).seat + "座");
                    } else {
                        result.get(i).status=1;
                        callBack.getBack("取消选座");
                        list.remove(s);
                        callBack.getStrng(list);
                    }
                }
            });


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MovieVIewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.che_box)
        CheckBox cheBox;
        public MovieVIewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface CallBack {
        void getBack(String s);
        void getStrng(List<String> list);
    }

    CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
