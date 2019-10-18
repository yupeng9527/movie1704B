package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.modle.bean.DetilBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class ForMoierAdapter extends XRecyclerView.Adapter<ForMoierAdapter.MovieViewHolder> {
    List<DetilBean.ResultBean.ShortFilmListBean> resultBeanList;


    public ForMoierAdapter(List<DetilBean.ResultBean.ShortFilmListBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_formovie, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        movieViewHolder.videoView.setUp(resultBeanList.get(i).videoUrl,null);
    }

    @Override
    public int getItemCount() {
        return resultBeanList == null ? 0 : resultBeanList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_view)
        JCVideoPlayer videoView;
        @BindView(R.id.lin_imag)
        LinearLayout linImag;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
