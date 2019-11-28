package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.SeenMovieBean;
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
public class SeenAdapter extends XRecyclerView.Adapter<SeenAdapter.MovieViewHolder> {
    List<SeenMovieBean.ResultBean> result;



    public SeenAdapter(List<SeenMovieBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cincme_seen, viewGroup, false);

        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.simpleView.setImageURI(result.get(i).imageUrl);
        movieViewHolder.textName.setText(result.get(i).movieName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm"+"观影");
        String format = formatter.format(result.get(i).beginTime);
        movieViewHolder.textTime.setText(format);
        movieViewHolder.butPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doView.onCurress(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simple_view)
        SimpleDraweeView simpleView;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.but_pl)
        Button butPl;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setDoView(DoView doView) {
        this.doView = doView;
    }

    DoView doView;

    public interface DoView {
        void onCurress(int i);
    }
}
