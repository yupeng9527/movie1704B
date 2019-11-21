package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.ScheduleListBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/9/29
 * author:贺少伟(盗)
 * function:
 */
public class ScheduleListAdapter extends XRecyclerView.Adapter<ScheduleListAdapter.MovieViewHolder> {
    List<ScheduleListBean.ResultBean> resultBeans;
    Context context;


    public ScheduleListAdapter(List<ScheduleListBean.ResultBean> resultBeans, Context context) {
        this.resultBeans = resultBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sched_movie, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.imagView.setImageURI(resultBeans.get(i).imageUrl);
        movieViewHolder.textName.setText(resultBeans.get(i).name);
        movieViewHolder.textDirector.setText("导演: " + resultBeans.get(i).director);
        movieViewHolder.textStarring.setText("主演: " + resultBeans.get(i).starring);
        movieViewHolder.textScore.setText("评分: " + resultBeans.get(i).score + "分");
        movieViewHolder.bitGaopiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               areaView.onCurress(resultBeans.get(i).movieId,resultBeans.get(i).trailerUrl,resultBeans.get(i).imageUrl);
            }
        });
        movieViewHolder.imagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.Deil");
                intent.putExtra("movieId",resultBeans.get(i).movieId);
                movieViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans == null ? 0 : resultBeans.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imag_view)
        SimpleDraweeView imagView;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_director)
        TextView textDirector;
        @BindView(R.id.text_starring)
        TextView textStarring;
        @BindView(R.id.text_score)
        TextView textScore;
        @BindView(R.id.bit_gaopiao)
        Button bitGaopiao;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setAreaView(AreaView areaView) {
        this.areaView = areaView;
    }

    AreaView areaView;

    public interface AreaView {
        void onCurress(int id, String ponth, String imag);
    }
}
