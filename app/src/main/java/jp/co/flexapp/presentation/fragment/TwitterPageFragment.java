package jp.co.flexapp.presentation.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.co.flexapp.R;
import jp.co.flexapp.common.util.TwitterUtils;
import jp.co.flexapp.infla.entity.Tweet;
import jp.co.flexapp.presentation.customVIew.TweetListAdapter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterPageFragment extends BasePageFragment {

    private OnFragmentInteractionListener mListener;
    private Twitter twitter;
    private TweetListAdapter adapter;

    public TwitterPageFragment() {
    }

    public static TwitterPageFragment newInstance(String param1, String param2) {
        TwitterPageFragment fragment = new TwitterPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccessToken accessToken = TwitterUtils.loadAccessToken(getContext());
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(getString(R.string.twitter_consumer_key))
                .setOAuthConsumerSecret(getString(R.string.twitter_consumer_secret))
                .setOAuthAccessToken(accessToken.getToken())
                .setOAuthAccessTokenSecret(accessToken.getTokenSecret());
        twitter = new TwitterFactory(cb.build()).getInstance();
        adapter = new TweetListAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter_page, container, false);

        ListView listView = (ListView) view.findViewById(R.id.twitter_list_view);

        Query query = new Query("hoge");
        query.setCount(50);
//        query.setQuery("watching now");
//        query.setSince("2016-10-01");
//        query.setUntil("2017-09-02");
//        query.setResultType(query.MIXED);
//        query.setCount(50);

        getSearchResultObservable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryResult>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(QueryResult queryResult) {
                        ArrayList<Tweet> list = new ArrayList<>();
                        for (Status tweetResult : queryResult.getTweets()) {
                            // Log.i("Tweet", tweet.getCreatedAt() + ":" + tweet.getUser().getName() + ":" + tweet.getText());
                            Tweet tweet = new Tweet();
                            tweet.setId(tweetResult.getFavoriteCount());
                            tweet.setTweet(tweetResult.getText());
                            tweet.setThumbNailId(R.drawable.sample_thumb);
                            tweet.setUsername(tweetResult.getUser().getName());
                            list.add(tweet);
                        }
                        adapter.setTweetList(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("Tweet", throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        adapter.setTweetList(new ArrayList<Tweet>());
        listView.setAdapter(adapter);

        LinearLayout layout = new LinearLayout(this.getContext());
        layout.addView(view);
        return layout;
    }

    private Observable<QueryResult> getSearchResultObservable(Query query) {
        return Observable.create(e -> {
            try {
                QueryResult queryResult = twitter.search(query);
                e.onNext(queryResult);
            } catch (TwitterException error) {
                e.onError(error);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
