package com.bw.movie.view.adapter;

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
import com.bw.movie.modle.bean.DetilBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class YuangyAdapter extends XRecyclerView.Adapter<YuangyAdapter.MovieViewHolder> {
    List<DetilBean.ResultBean.MovieActorBean> resultBeanList;


    public YuangyAdapter(List<DetilBean.ResultBean.MovieActorBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_daoy, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.imagView.setImageURI(resultBeanList.get(i).photo);
        movieViewHolder.textScore.setText(resultBeanList.get(i).name);


    }

    @Override
    public int getItemCount() {
        return resultBeanList == null ? 0 : resultBeanList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imag_view)
        SimpleDraweeView imagView;
        @BindView(R.id.text_score)
        TextView textScore;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
