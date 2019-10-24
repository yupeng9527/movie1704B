package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.SchedBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther:
 * @Date: 2019/10/2 16:16:24
 * @Description:
 */
public class RommSeatAdapter extends XRecyclerView.Adapter<RommSeatAdapter.MovieVIewHolder> {

    List<SchedBean.ResultBean> result;
    public RommSeatAdapter(List<SchedBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_room, viewGroup, false);
        return new MovieVIewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVIewHolder movieVIewHolder, final int i) {
        String beginTime = result.get(i).beginTime;
        String endTime = result.get(i).endTime;
        movieVIewHolder.recyclerType.setText(result.get(i).screeningHall);
        movieVIewHolder.recyclerBegintime.setText(beginTime);
        movieVIewHolder.recyclerEndtime.setText(endTime);
        movieVIewHolder.reLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int blue = 0;
                movieVIewHolder.reLay.setBackgroundColor(blue);
                callBack.getBack("0");
                callBack.getId(result.get(i).id);
            }
        });
        callBack.getId(result.get(i).id);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MovieVIewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_type)
        TextView recyclerType;
        @BindView(R.id.recycler_Begintime)
        TextView recyclerBegintime;
        @BindView(R.id.recycler_endtime)
        TextView recyclerEndtime;
        @BindView(R.id.re_lay)
        RelativeLayout reLay;

        public MovieVIewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface iCallBack {
        void getBack(String s);
        void getId(int idd);
    }

   iCallBack callBack;

    public void setCallBack(iCallBack callBack) {
        this.callBack = callBack;
    }
}
