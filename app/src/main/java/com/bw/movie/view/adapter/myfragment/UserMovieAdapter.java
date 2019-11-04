package com.bw.movie.view.adapter.myfragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.modle.bean.UserBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/10/10
 * author:贺少伟(盗)
 * function:
 */
public class UserMovieAdapter extends XRecyclerView.Adapter<UserMovieAdapter.SoonViewHolder> {
    List<UserBean.ResultBean> resultBeans;
    public UserMovieAdapter(List<UserBean.ResultBean> resultBeans) {
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public SoonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new SoonViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SoonViewHolder soonViewHolder, final int i) {
        Glide.with(soonViewHolder.itemView.getContext())
                .load(resultBeans.get(i).imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(soonViewHolder.imagSoon);
        soonViewHolder.textMoviename.setText(resultBeans.get(i).name);
        soonViewHolder.textNum.setText(resultBeans.get(i).wantSeeNum+"人想看");
        final long releaseTime = resultBeans.get(i).releaseTime;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String format = sdf.format(releaseTime);
        soonViewHolder.textSooktime.setText(format+"上映");
        soonViewHolder.imagSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.bawei.Deil");
                intent.putExtra("movieId",resultBeans.get(i).movieId);
                soonViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans==null?0:resultBeans.size();
    }

    public class SoonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imag_soon)
        ImageView imagSoon;
        @BindView(R.id.text_moviename)
        TextView textMoviename;
        @BindView(R.id.text_sooktime)
        TextView textSooktime;
        @BindView(R.id.text_num)
        TextView textNum;
        public SoonViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setIview(Iview iview) {
        this.iview = iview;
    }

    private Iview iview;
    public interface Iview{
        void onCurr(int i);
    }


}
