package com.bw.movie.view.zview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class SearchView extends RelativeLayout {


    @BindView(R.id.text_finis)
    TextView textFinis;
    @BindView(R.id.edit_ss)
    EditText editSs;
    @BindView(R.id.text_ann)
    TextView textAnn;

    public SearchView(Context context) {
        super(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context)
                .inflate(R.layout.item_view, this, true);
        ButterKnife.bind(inflate);

    }

    @OnClick({R.id.text_finis, R.id.edit_ss, R.id.text_ann})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_finis:
                if (onIntersen!=null) {
                    onIntersen.onFinis("");
                }
                break;
            case R.id.edit_ss:
                if (onIntersen!=null) {
                    String s = editSs.getText().toString();
                    onIntersen.onSs(s);
                }
                break;
            case R.id.text_ann:
                editSs.setVisibility(VISIBLE);
                textAnn.setVisibility(GONE);
                break;
        }
    }

    public void setOnIntersen(OnIntersen onIntersen) {
        this.onIntersen = onIntersen;
    }

    private OnIntersen onIntersen;
    public interface OnIntersen{
        void onFinis(String str);
        void onSs(String str);
    }
}
