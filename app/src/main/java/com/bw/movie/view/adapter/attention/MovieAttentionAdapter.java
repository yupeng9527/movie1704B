package com.bw.movie.view.adapter.attention;

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
import com.bw.movie.modle.bean.OnMovieBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/9/29
 * author:贺少伟(盗)
 * function:
 */
public class MovieAttentionAdapter extends XRecyclerView.Adapter<MovieAttentionAdapter.MovieViewHolder> {
    List<OnMovieBean.ResultBean> result;

    public MovieAttentionAdapter(List<OnMovieBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movieatticon, viewGroup, false);

        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Glide.with(movieViewHolder.itemView.getContext())
                .load(result.get(i).imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(movieViewHolder.imagView);
        movieViewHolder.textName.setText(result.get(i).name);
        movieViewHolder.textDirector.setText("导演: "+result.get(i).director);
        movieViewHolder.textStarring.setText("主演: "+result.get(i).starring);
        movieViewHolder.textScore.setText("评分: "+result.get(i).score+"分");
    }

    @Override
    public int getItemCount() {
        return result==null?0:result.size();
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
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
