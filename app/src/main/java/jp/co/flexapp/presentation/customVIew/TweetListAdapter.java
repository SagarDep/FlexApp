package jp.co.flexapp.presentation.customVIew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import jp.co.flexapp.R;
import jp.co.flexapp.infla.Tweet;

/**
 * Created by mitsuhori_y on 2017/08/30.
 */

public class TweetListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater = null;
    ArrayList<Tweet> tweetList;

    public TweetListAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTweetList(ArrayList<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public int getCount() {
        return tweetList.size();
    }

    @Override
    public Object getItem(int position) {
        return tweetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return tweetList;
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.twitter_list_component, parent, false);
        // ((TextView) convertView.findViewById(R.id.twitter_tweet)).setText(tweetList.get(position).getName());
        return convertView;
    }
}
