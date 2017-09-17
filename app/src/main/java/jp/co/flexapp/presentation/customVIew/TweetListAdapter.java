package jp.co.flexapp.presentation.customVIew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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

    private Observable<Bitmap> getUserImageBmpObservable(String url) {
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap image;
                try {
                    URL imageUrl = new URL(url);
                    InputStream imageIs = imageUrl.openStream();
                    image = BitmapFactory.decodeStream(imageIs);
                    e.onNext(image);
                } catch (MalformedURLException err) {
                    e.onError(err);
                } catch (IOException err) {
                    e.onError(err);
                }
            }
        });
//        observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Bitmap>() {
//                    @Override
//                    public void onSubscribe(Disposable disposable) {
//
//                    }
//
//                    @Override
//                    public void onNext(Bitmap bitmap) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
