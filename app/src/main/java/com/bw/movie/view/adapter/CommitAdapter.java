package com.bw.movie.view.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.CommentBean;
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
public class CommitAdapter extends XRecyclerView.Adapter<CommitAdapter.MovieViewHolder> {
    List<CommentBean.ResultBean> resultBeans;



    public CommitAdapter(List<CommentBean.ResultBean> resultBeans) {
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_commit, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
//        if (resultBeans.get(i).commentHeadPic != null) {
//            Glide.with(movieViewHolder.itemView.getContext())
//                    .load(resultBeans.get(i).commentHeadPic)
//                    .error(R.mipmap.ic_launcher)
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                    .into(movieViewHolder.commitImageName);
//        }else{
//
//
//        }

        movieViewHolder.textName.setText(resultBeans.get(i).commentContent);
//        movieViewHolder.commitTextName.setText(resultBeans.get(i).commentUserName);
//        movieViewHolder.commitRatBar.setNumStars(resultBeans.get(i).score);

    }

    @Override
    public int getItemCount() {
        return resultBeans == null ? 0 : resultBeans.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commit_image_name)
        SimpleDraweeView commitImageName;
        @BindView(R.id.commit_text_name)
        TextView commitTextName;
        @BindView(R.id.commit_rat_bar)
        RatingBar commitRatBar;
        @BindView(R.id.text_name)
        TextView textName;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
