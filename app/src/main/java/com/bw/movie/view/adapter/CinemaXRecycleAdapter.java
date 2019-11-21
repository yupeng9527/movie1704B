package com.bw.movie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.modle.bean.BannerBean;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.fragment.YMovieFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * @Auther:张贺祥(Lenovo)
 * @Date:2019/11/14
 * @Description:
 */
public class CinemaXRecycleAdapter extends XRecyclerView.Adapter implements IViewContract.doView {




    private List<MoVieListBean.ResultBean> resulta;
    private List<SoonMovieBean.ResultBean> resultb;
    private List<HotBean.ResultBean> resultc;
    private Context context;

    public CinemaXRecycleAdapter(Context context) {
        this.context = context;
    }

    public void getResultA(List<MoVieListBean.ResultBean> resulta) {
        this.resulta = resulta;
    }

    public void getResultB(List<SoonMovieBean.ResultBean> resultb) {
        this.resultb = resultb;
    }

    public void getResultC(List<HotBean.ResultBean> resultc) {
        this.resultc = resultc;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_do_not, viewGroup, false);
            return new ViewHolderA(inflate);
        } else if (i == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_do_show, viewGroup, false);
            return new ViewHolderB(inflate);
        } else if (i == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_do_movie, viewGroup, false);
            return new ViewHolderC(inflate);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            if (viewHolder instanceof ViewHolderA) {
                ViewHolderA viewHolderA = (ViewHolderA) viewHolder;
                YMovieAdapter yMovieAdapter = new YMovieAdapter(resulta,context);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                viewHolderA.xlistNot.setLayoutManager(linearLayoutManager);
                viewHolderA.xlistNot.setAdapter(yMovieAdapter);
                viewHolderA.butTextNot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("com.bawei.sh");
                    context.startActivity(intent);
                }
             });
                yMovieAdapter.setAreaView(new YMovieAdapter.AreaView() {
                    @Override
                    public void onCurress(int id) {
                        Intent intent1 = new Intent("com.bawei.SelectMovie");
                        intent1.putExtra("movieId",id);
                        viewHolder.itemView.getContext().startActivity(intent1);
                    }
                });
            }
        } else if (itemViewType == 2) {
            if (viewHolder instanceof ViewHolderB) {
                ViewHolderB viewHolderB = (ViewHolderB) viewHolder;
                final SoonMovieAdapter soonMovieAdapter = new SoonMovieAdapter(resultb,context);
                viewHolderB.xlistShow.setLayoutManager(new LinearLayoutManager(context));
                viewHolderB.xlistShow.setAdapter(soonMovieAdapter);
                soonMovieAdapter.notifyDataSetChanged();
                soonMovieAdapter.setIview(new SoonMovieAdapter.Iview() {
                    @Override
                    public void onCurr(int i) {
                        SharedPreferences sp = context.getSharedPreferences("feil", Context.MODE_PRIVATE);
                        String sessionId = sp.getString("sessionId", null);
                        int userId = sp.getInt("userId", 0);
                        Map<String, Object> map = new HashMap<>();
                        Map<String, Object> smap = new HashMap<>();
                        map.put("userId", userId);
                        map.put("sessionId", sessionId);
                        smap.put("movieId", i);
                        Persenter persenter = new Persenter(CinemaXRecycleAdapter.this);
                        persenter.doResert(map, smap);

                    }
                });
                viewHolderB.butTextShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.bawei.sh");
                        context.startActivity(intent);
                    }
                });
            }
        } else if (itemViewType == 3) {
            if (viewHolder instanceof ViewHolderC) {
                ViewHolderC viewHolderC = (ViewHolderC) viewHolder;
                HotAdapter hotAdapter = new HotAdapter(resultc,context);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                viewHolderC.xlistMovie.setLayoutManager(linearLayoutManager);
                viewHolderC.xlistMovie.setAdapter(hotAdapter);
                viewHolderC.butTextNotMovie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.bawei.sh");
                        context.startActivity(intent);
                    }
                });
                hotAdapter.setAreaView(new HotAdapter.AreaView() {
                    @Override
                    public void onCurress(int id) {
                        Intent intent1 = new Intent("com.bawei.SelectMovie");
                        intent1.putExtra("movieId",id);
                        viewHolder.itemView.getContext().startActivity(intent1);
                    }
                });
                viewHolderC.imagView.setImageURI(resultc.get(0).horizontalImage);
                viewHolderC.textName.setText(resultc.get(0).name);
                viewHolderC.textScore.setText(resultc.get(0).score + "分");

            }
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 2;
        } else if (position == 2) {
            return 3;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onLogCurress(Object obj) {

    }

    @Override
    public void onShapeCurress(Object obj) {

    }

    @Override
    public void onMyCurress(Object obj) {

    }

    @Override
    public void onBannerCurress(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {

    }

    public class ViewHolderA extends XRecyclerView.ViewHolder {
        @BindView(R.id.but_text_not)
        TextView butTextNot;
        @BindView(R.id.xlist_not)
        RecyclerView xlistNot;

        public ViewHolderA(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public class ViewHolderB extends XRecyclerView.ViewHolder {

        @BindView(R.id.but_text_show)
        TextView butTextShow;
        @BindView(R.id.xlist_show)
        RecyclerView xlistShow;

        public ViewHolderB(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }



    public class ViewHolderC extends XRecyclerView.ViewHolder {

        @BindView(R.id.but_text_not_movie)
        TextView butTextNotMovie;
        @BindView(R.id.imag_view)
        SimpleDraweeView imagView;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_score)
        TextView textScore;
        @BindView(R.id.bit_gaopiao)
        Button bitGaopiao;
        @BindView(R.id.xlist_movie)
        RecyclerView xlistMovie;

        public ViewHolderC(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
