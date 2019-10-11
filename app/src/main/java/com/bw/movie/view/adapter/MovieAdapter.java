package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.MoVieListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/9/29
 * author:贺少伟(盗)
 * function:
 */
public class MovieAdapter extends XRecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    ArrayList<MoVieListBean.ResultBean> resultBeans;
    public MovieAdapter(ArrayList<MoVieListBean.ResultBean> resultBeans) {
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);

        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Glide.with(movieViewHolder.itemView.getContext())
                .load(resultBeans.get(i).imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(movieViewHolder.imagView);
        movieViewHolder.textName.setText(resultBeans.get(i).name);
        movieViewHolder.textDirector.setText("导演: "+resultBeans.get(i).director);
        movieViewHolder.textStarring.setText("主演: "+resultBeans.get(i).starring);
        movieViewHolder.textScore.setText("评分: "+resultBeans.get(i).score+"分");
        movieViewHolder.bitGaopiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context, "正在购票中", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans==null?0:resultBeans.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imag_view)
        ImageView imagView;
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
            ButterKnife.bind(this,itemView);
        }
    }
}
