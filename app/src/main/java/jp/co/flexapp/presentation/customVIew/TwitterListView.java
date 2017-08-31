package jp.co.flexapp.presentation.customVIew;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import jp.co.flexapp.R;

/**
 * Created by mitsuhori_y on 2017/08/16.
 */

public class TwitterListView extends LinearLayout {

    public TwitterListView(Context context) {
        super(context, null);
    }

    public TwitterListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.twitter_list_component, this);
    }

    public TwitterListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.twitter_list_component, this);
    }
}
