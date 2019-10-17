package com.bw.movie.view.adapter;

import android.content.Intent;
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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.MoVieListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class YMovieAdapter extends XRecyclerView.Adapter<YMovieAdapter.MovieViewHolder> {
    List<MoVieListBean.ResultBean> resultBeanList;

    public YMovieAdapter(List<MoVieListBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ymovie, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        Glide.with(movieViewHolder.itemView.getContext())
                .load(resultBeanList.get(i).imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .error(R.mipmap.ic_launcher)
                .into(movieViewHolder.imagView);
        movieViewHolder.textName.setText(resultBeanList.get(i).name);
        movieViewHolder.textScore.setText(resultBeanList.get(i).score+"分");
        movieViewHolder.bitGaopiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context, "正在购票中", Toast.LENGTH_SHORT).show();
            }
        });
        movieViewHolder.imagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.Deil");
                intent.putExtra("movieId",resultBeanList.get(i).movieId);
                movieViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeanList == null ? 0 : resultBeanList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imag_view)
        ImageView imagView;
        @BindView(R.id.text_score)
        TextView textScore;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.bit_gaopiao)
        Button bitGaopiao;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
