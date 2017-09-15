package jp.co.flexapp.presentation.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.co.flexapp.R;
import jp.co.flexapp.common.util.TwitterUtils;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by mitsuhori_y on 2017/09/05.
 */

public class TwitterOAuthActivity extends AppCompatActivity {

    private final String OAUTH_VERIFIER = "oauth_verifier";

    private String callBackURL;
    private Twitter twitter;
    private RequestToken requestToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callBackURL = getString(R.string.twitter_callback_url);

        twitter = TwitterUtils.getTwitterInstance(this);

        getTwitterRequestTokenObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(String url) {
                        Log.i("twitter_token", url);
                        if (!url.isEmpty()) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        } else {
                            Log.e("twitter", "tokenの取得に失敗しました");
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (intent == null || intent.getData() == null || !intent.getData().toString().startsWith(callBackURL)) {
            return;
        }

        //URLによって実行されたアプリから引数を取得する
        String verifier = intent.getData().getQueryParameter(OAUTH_VERIFIER);
        getTwitterAccessTokenObservable(verifier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<AccessToken>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(AccessToken accessToken) {
                        if (accessToken != null) {
                            showToast("認証成功！");
                            successOAuth(accessToken);
                        } else {
                            showToast("認証失敗...");
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void successOAuth(AccessToken accessToken) {
        //Utilクラスからトークン登録メソッドを呼び出し
        TwitterUtils.storeAccessToken(this, accessToken);

        //MainActivityへ遷移
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    private Observable<String> getTwitterRequestTokenObservable() {
        return Observable.create(
                e -> {
                    requestToken = twitter.getOAuthRequestToken(callBackURL);
                    e.onNext(requestToken.getAuthorizationURL());
                }
        );
    }

    private Observable<AccessToken> getTwitterAccessTokenObservable(String... verifier) {
        return Observable.create(
                e -> e.onNext(twitter.getOAuthAccessToken(requestToken, verifier[0]))
        );
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}
