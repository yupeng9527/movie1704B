package com.bw.movie.view.adapter;

import android.content.Intent;
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
public class HtoAdapter extends XRecyclerView.Adapter<HtoAdapter.MovieViewHolder> {
    List<HotBean.ResultBean> resultBeanList;


    public HtoAdapter(List<HotBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_hto, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
            movieViewHolder.imagView.setImageURI(resultBeanList.get(i).imageUrl);
//        Glide.with(movieViewHolder.itemView.getContext())
//                .load(resultBeanList.get(i).imageUrl)
//                .error(R.mipmap.ic_launcher)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(movieViewHolder.imagView);
        movieViewHolder.textName.setText(resultBeanList.get(i).name);
        movieViewHolder.textDirector.setText("导演:  "+resultBeanList.get(i).director);
        movieViewHolder.textStarring.setText("演员:  "+resultBeanList.get(i).starring);
        movieViewHolder.textScore.setText(resultBeanList.get(i).score + "分");
        movieViewHolder.bitGaopiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaView.onCurress(resultBeanList.get(i).movieId);
            }
        });

        movieViewHolder.imagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.bawei.Deil");
                intent.putExtra("movieId", resultBeanList.get(i).movieId);
                movieViewHolder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultBeanList == null ? 0 : resultBeanList.size();
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
        @BindView(R.id.text_score)
        TextView textScore;
        @BindView(R.id.bit_gaopiao)
        Button bitGaopiao;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
    public void setAreaView(AreaView areaView) {
        this.areaView = areaView;
    }

    AreaView areaView;

    public interface AreaView {
        void onCurress(int id);
    }
}
