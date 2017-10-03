package jp.co.flexapp.presentation.customVIew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jp.co.flexapp.R;
import jp.co.flexapp.infla.entity.FbMsg;
import lombok.Setter;

/**
 * Created by mitsuhori_y on 2017/10/03.
 */

public class FbListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater = null;
    @Setter
    ArrayList<FbMsg> fbMsgList;

    public FbListAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fbMsgList.size();
    }

    @Override
    public Object getItem(int i) {
        return fbMsgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return fbMsgList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fb_list_component, viewGroup, false);
        ((TextView) view.findViewById(R.id.fb_message)).setText(fbMsgList.get(i).getMessage());
        ((TextView) view.findViewById(R.id.fb_createdAt)).setText(fbMsgList.get(i).getCreatedAt());
        return view;
    }
}
