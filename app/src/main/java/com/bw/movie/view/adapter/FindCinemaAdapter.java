package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.CinemaByBean;
import com.bw.movie.modle.bean.RegionListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/11
 * author:贺少伟(盗)
 * function:
 */
public class FindCinemaAdapter extends XRecyclerView.Adapter<FindCinemaAdapter.MovieViewHolder> {
    List<CinemaByBean.ResultBean> result;


    public FindCinemaAdapter(List<CinemaByBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_find_area, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.textNameData.setText(result.get(i).name);
        movieViewHolder.textNameData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieViewHolder.textNameData.setTextSize(14);
                areaView.onCurress(result.get(i).id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name_data)
        TextView textNameData;;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setAreaView(AreaView areaView) {
        this.areaView = areaView;
    }

    AreaView areaView;
    public interface AreaView{
        void onCurress(int id);
    }
}
