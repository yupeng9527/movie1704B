package com.bw.movie.view.adapter;

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
import com.bw.movie.modle.bean.HotBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class HotonAdapter extends XRecyclerView.Adapter<HotonAdapter.MovieViewHolder> {
    List<HotBean.ResultBean> resultBeanList;

    public HotonAdapter(List<HotBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ovie, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Glide.with(movieViewHolder.itemView.getContext())
                .load(resultBeanList.get(0).imageUrl)
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(movieViewHolder.imagView);
        movieViewHolder.textName.setText(resultBeanList.get(0).name);
        movieViewHolder.textScore.setText(resultBeanList.get(0).score+"分");
        movieViewHolder.bitGaopiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.context, "正在购票中", Toast.LENGTH_SHORT).show();
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
