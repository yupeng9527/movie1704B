package com.bw.movie.view.adapter.discuss;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaDiscussBean;
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
public class CinemaDiscussAdapter extends XRecyclerView.Adapter<CinemaDiscussAdapter.MovieViewHolder> {
    List<CinemaDiscussBean.ResultBean> result;



    public CinemaDiscussAdapter(List<CinemaDiscussBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cinemdiscuss, viewGroup, false);

        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.imagView.setImageURI(result.get(i).logo);
        movieViewHolder.textName.setText(result.get(i).cinemaName);
        movieViewHolder.textDirector.setText( result.get(i).address);
        movieViewHolder.textStarring.setText(result.get(i).distance+"km");
        movieViewHolder.textDiscussContent.setText(result.get(i).myCommentContent);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(result.get(i).commentTime);
        movieViewHolder.textDiscussTime.setText(format);
    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
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
        @BindView(R.id.text_discuss_name)
        TextView textDiscussName;
        @BindView(R.id.text_discuss_content)
        TextView textDiscussContent;
        @BindView(R.id.text_discuss_time)
        TextView textDiscussTime;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
