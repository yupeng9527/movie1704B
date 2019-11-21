package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.ByRegionBean;
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
public class CinemaByAdapter extends XRecyclerView.Adapter<CinemaByAdapter.MovieViewHolder> {
    List<ByRegionBean.ResultBean> resultBeanList;



    public CinemaByAdapter(List<ByRegionBean.ResultBean> resultBeanList) {
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
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.imagView.setImageURI(resultBeanList.get(i).logo);
        movieViewHolder.textName.setText(resultBeanList.get(i).address);
        movieViewHolder.textScore.setText(resultBeanList.get(i).name);
        movieViewHolder.textPrice.setText(resultBeanList.get(i).price+"元");
        movieViewHolder.linLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaView.onCurress(resultBeanList.get(i).cinemaId,resultBeanList.get(i).price);
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
        @BindView(R.id.text_score)
        TextView textScore;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_price)
        TextView textPrice;
        @BindView(R.id.lin_layout)
        LinearLayout linLayout;

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
        void onCurress(int id,Double price);
    }
}
