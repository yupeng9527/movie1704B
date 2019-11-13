package com.bw.movie.view.adapter.buy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.TICketBean;
import com.facebook.drawee.view.SimpleDraweeView;
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
public class BuyyiMoierAdapter extends XRecyclerView.Adapter<BuyyiMoierAdapter.MovieViewHolder> {
    List<TICketBean.ResultBean> resultBeanList;


    public BuyyiMoierAdapter(List<TICketBean.ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_buyyimovie, viewGroup, false);
        return new MovieViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int i) {
        movieViewHolder.buyImagView.setImageURI(resultBeanList.get(i).imageUrl);
//        Glide.with(movieViewHolder.itemView.getContext())
//                .load(resultBeanList.get(i).imageUrl)
//                .error(R.mipmap.ic_launcher)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(movieViewHolder.buyImagView);
        movieViewHolder.buyTextName.setText(resultBeanList.get(i).movieName);
        movieViewHolder.buyTextDingDa.setText("订 单 号  " + resultBeanList.get(i).orderId);
        long createTime = resultBeanList.get(i).createTime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = formatter.format(createTime);
        movieViewHolder.buyTextDingTime.setText("创建时间  " + format);
        movieViewHolder.buyTextXiangQing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBuy.onCurress(resultBeanList.get(i).orderId,resultBeanList.get(i).imageUrl);
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultBeanList == null ? 0 : resultBeanList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.buy_imag_view)
        SimpleDraweeView buyImagView;
        @BindView(R.id.buy_text_name)
        TextView buyTextName;
        @BindView(R.id.buy_text_ding_da)
        TextView buyTextDingDa;
        @BindView(R.id.buy_text_ding_time)
        TextView buyTextDingTime;
        @BindView(R.id.buy_text_xiang_qing)
        TextView buyTextXiangQing;
        @BindView(R.id.lin_imag)
        LinearLayout linImag;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setOnBuyyi(OnBuyyi onBuy) {
        this.onBuy = onBuy;
    }

    public OnBuyyi onBuy;

    public interface OnBuyyi {
        void onCurress(String str,String imag);
    }
}
