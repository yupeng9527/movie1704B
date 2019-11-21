package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CommentBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/9/29
 * author:贺少伟(盗)
 * function:
 */
public class CinAdapter extends XRecyclerView.Adapter<CinAdapter.MovieViewHolder> {
    List<CommentBean.ResultBean> resultBeans;


    public CinAdapter(List<CommentBean.ResultBean> resultBeans) {
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cincme_movie, viewGroup, false);

        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.imagView.setImageURI(resultBeans.get(i).commentHeadPic);
        movieViewHolder.textCimemaName.setText(resultBeans.get(i).commentUserName);
        long commentTime = resultBeans.get(i).commentTime;
        movieViewHolder.filmReviewRatingBar.setRating((float) resultBeans.get(i).score);
        movieViewHolder.textScoreBar.setText(resultBeans.get(i).score+"分");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(commentTime);
        movieViewHolder.textCimemaTime.setText(format1);
        movieViewHolder.textCimemaPinlu.setText(resultBeans.get(i).commentContent);

        final int isGreat = resultBeans.get(i).isGreat;

        if (isGreat== 0) {
            movieViewHolder.imagTrue.setVisibility(View.VISIBLE);
        } else if (isGreat == 1) {
            movieViewHolder.imagFalse.setVisibility(View.VISIBLE);
            movieViewHolder.imagTrue.setVisibility(View.GONE);
        }
        movieViewHolder.textCinemaNumRan.setText(resultBeans.get(i).greatNum + "");
        movieViewHolder.textCinemaNum.setText("等" + resultBeans.get(i).greatNum + "人进行了回复");
        movieViewHolder.imagTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doView.onCurress(resultBeans.get(i).commentId);
                movieViewHolder.imagFalse.setVisibility(View.VISIBLE);
                movieViewHolder.imagTrue.setVisibility(View.GONE);
                movieViewHolder.textCinemaNumRan.setText(resultBeans.get(i).greatNum + "");
            }
        });
        movieViewHolder.imagFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doView.onCurress(resultBeans.get(i).commentId);
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
        @BindView(R.id.text_cimema_name)
        TextView textCimemaName;
        @BindView(R.id.filmReview_ratingBar)
        RatingBar filmReviewRatingBar;
        @BindView(R.id.text_score_bar)
        TextView textScoreBar;
        @BindView(R.id.text_cimema_time)
        TextView textCimemaTime;
        @BindView(R.id.text_cimema_pinlu)
        TextView textCimemaPinlu;
        @BindView(R.id.imag_true)
        ImageView imagTrue;
        @BindView(R.id.imag_false)
        ImageView imagFalse;
        @BindView(R.id.text_cinema_num_ran)
        TextView textCinemaNumRan;
        @BindView(R.id.text_cinema_num)
        TextView textCinemaNum;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setDoView(DoView doView) {
        this.doView = doView;
    }

    DoView doView;
    public interface DoView{
        void onCurress(int id);
    }
}
