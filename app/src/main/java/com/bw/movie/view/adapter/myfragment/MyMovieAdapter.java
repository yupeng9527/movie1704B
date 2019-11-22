package com.bw.movie.view.adapter.myfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.MyMovieBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/10
 * author:贺少伟(盗)
 * function:
 */
public class MyMovieAdapter extends XRecyclerView.Adapter<MyMovieAdapter.SoonViewHolder> {
    List<MyMovieBean.ResultBean> resultBeans;


    public MyMovieAdapter(List<MyMovieBean.ResultBean> resultBeans) {
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public SoonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_my_moview, viewGroup, false);
        return new SoonViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SoonViewHolder soonViewHolder, final int i) {
        soonViewHolder.textMovie.setText(resultBeans.get(i).movieName);
        soonViewHolder.textPiaoWei.setText(resultBeans.get(i).seat);
        soonViewHolder.textMyMovie.setText(resultBeans.get(i).cinemaName);
        soonViewHolder.textMyYt.setText(resultBeans.get(i).screeningHall);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long createTime = resultBeans.get(i).createTime;
        String format = sdf.format(createTime);
        soonViewHolder.textMyTime.setText(format);
        soonViewHolder.textMyTi.setText(resultBeans.get(i).endTime);
        soonViewHolder.butQu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iview.onCurr(resultBeans.get(i).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans == null ? 0 : resultBeans.size();
    }

    public class SoonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_movie)
        TextView textMovie;
        @BindView(R.id.text_piao_wei)
        TextView textPiaoWei;
        @BindView(R.id.text_my_movie)
        TextView textMyMovie;
        @BindView(R.id.text_my_yt)
        TextView textMyYt;
        @BindView(R.id.text_my_time)
        TextView textMyTime;
        @BindView(R.id.text_my_ti)
        TextView textMyTi;
        @BindView(R.id.but_qu)
        Button butQu;
        public SoonViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setIview(onMovie iview) {
        this.iview = iview;
    }

    private onMovie iview;

    public interface onMovie {
        void onCurr(int i);
    }


}
