package com.bw.movie.view.zview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class SearchView extends RelativeLayout {


    @BindView(R.id.imag_mapa)
    ImageView imagMapa;
    @BindView(R.id.text_mapa_name)
    TextView textMapaName;
    @BindView(R.id.imag_search)
    ImageView imagSearch;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.text_visih)
    TextView textVisih;

    public SearchView(Context context) {
        super(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context)
                .inflate(R.layout.item_view, this, true);
        ButterKnife.bind(inflate);
        imagSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                imagSearch.setVisibility(GONE);
                editText.setVisibility(VISIBLE);
                String s = editText.getText().toString();
                onIntersen.onFinis(s);
            }
        });
        textVisih.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(GONE);
                onIntersen.onSs("a");
            }
        });
    }

    public void setOnIntersen(OnIntersen onIntersen) {
        this.onIntersen = onIntersen;
    }

    private OnIntersen onIntersen;

    public interface OnIntersen {
        void onFinis(String str);

        void onSs(String str);
    }

}
