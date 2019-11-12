package com.bw.movie.view.adapter.attention;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaBean;
import com.bw.movie.modle.bean.OnCimenBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class CinemaAttentionAdapter extends XRecyclerView.Adapter<CinemaAttentionAdapter.MovieViewHolder> {
    List<OnCimenBean.ResultBean> resultBeanList;


    public CinemaAttentionAdapter(List<OnCimenBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recommed, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        Glide.with(movieViewHolder.itemView.getContext())
                .load(resultBeanList.get(i).logo)
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(movieViewHolder.imagView);
        movieViewHolder.textName.setText(resultBeanList.get(i).address);
        movieViewHolder.textScore.setText(resultBeanList.get(i).name);
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
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}