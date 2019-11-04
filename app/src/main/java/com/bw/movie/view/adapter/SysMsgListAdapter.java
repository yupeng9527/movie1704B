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
import com.bw.movie.modle.bean.CinemaBean;
import com.bw.movie.modle.bean.SysMsgListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class SysMsgListAdapter extends XRecyclerView.Adapter<SysMsgListAdapter.MovieViewHolder> {
    List<SysMsgListBean.ResultBean> resultBeanList;
    public SysMsgListAdapter(List<SysMsgListBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sysomolist, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        int status = resultBeanList.get(i).status;
        if (status==0){
            movieViewHolder.checkBox.setVisibility(View.VISIBLE);
        }else if (status==1){
            movieViewHolder.checkBox.setVisibility(View.GONE);
        }
        movieViewHolder.textName.setText(resultBeanList.get(i).title);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(resultBeanList.get(i).pushTime);
        movieViewHolder.textTime.setText(format);
        movieViewHolder.textXianq.setText(resultBeanList.get(i).content);
    }

    @Override
    public int getItemCount() {
        return resultBeanList == null ? 0 : resultBeanList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_box)
        ImageView checkBox;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_xianq)
        TextView textXianq;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
