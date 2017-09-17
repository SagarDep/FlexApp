package jp.co.flexapp.presentation.customVIew;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jp.co.flexapp.R;
import jp.co.flexapp.infla.entity.Tweet;
import lombok.Setter;

/**
 * Created by mitsuhori_y on 2017/08/30.
 */

public class TweetListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater = null;
    @Setter
    ArrayList<Tweet> tweetList;

    public TweetListAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return tweetList.get(position).getId();
    }

    public void setImageOfList(int index, Bitmap image) {
        Tweet tweet = tweetList.get(index);
        tweet.setUserImage(image);
        tweetList.set(index, tweet);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.twitter_list_component, parent, false);
        ((TextView) convertView.findViewById(R.id.twitter_username)).setText(tweetList.get(position).getUsername());
        ((TextView) convertView.findViewById(R.id.twitter_tweet)).setText(tweetList.get(position).getTweet());
        ((TextView) convertView.findViewById(R.id.twitter_createdAt)).setText(tweetList.get(position).getCreatedAt());
        ((ImageView) convertView.findViewById(R.id.twitter_thumb)).setImageBitmap(tweetList.get(position).getUserImage());
        return convertView;
    }
}
