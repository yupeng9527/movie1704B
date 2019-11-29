package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.HotBean;
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
public class AreaAdapter extends XRecyclerView.Adapter<AreaAdapter.MovieViewHolder> {
    List<RegionListBean.ResultBean> result;


    public AreaAdapter(List<RegionListBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_area, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.textNameData.setText(result.get(i).regionName);
        movieViewHolder.textNameData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areaView!=null) {
                    areaView.onCurress(result.get(i).regionId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name_data)
        TextView textNameData;

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
