package jp.co.flexapp.presentation.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.co.flexapp.R;
import jp.co.flexapp.infla.db.FbAccessToken;
import jp.co.flexapp.presentation.FlexApp;
import jp.co.flexapp.presentation.activity.FbOAuthActivity;

import static android.view.View.VISIBLE;

public class FbPageFragment extends BasePageFragment {

    private OnFragmentInteractionListener mListener;
    private Button toLoginBtn;
    private ProgressBar progressBar;

    public FbPageFragment() {
    }

    public static FbPageFragment newInstance(String param1, String param2) {
        FbPageFragment fragment = new FbPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//キーハッシュ確認用
//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
//                    "jp.co.flexapp",
//                    PackageManager.GET_SIGNATURES);
//            for (android.content.pm.Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fb_page, container, false);
        FacebookSdk.sdkInitialize(this.getActivity());
        toLoginBtn = (Button) view.findViewById(R.id.fb_login_btn);
        progressBar = (ProgressBar) view.findViewById(R.id.fb_progress_bar);
        Single<FbAccessToken> token = FlexApp.get().getDB().fbAccessTokenDao().getFbToken();
        token.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<FbAccessToken>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(FbAccessToken fbAccessToken) {
                Log.i("TOKEN", fbAccessToken.getAccessToken());
                progressBar.setVisibility(VISIBLE);

                new GraphRequest(getAccessToken(fbAccessToken),
                        "me/feed",
                        null,
                        HttpMethod.GET,
                        res -> {
                            Log.d("res", res.getRawResponse());
                        }).executeAsync();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TOKEN", e.getStackTrace().toString());
                toLoginBtn.setVisibility(VISIBLE);
                toLoginBtn.setOnClickListener(v -> {
                    startActivity(FbOAuthActivity.makeIntent(getActivity()));
                });
            }
        });
        return view;
    }

    private AccessToken getAccessToken(FbAccessToken fbAccessToken) {
        return new AccessToken
                (fbAccessToken.getAccessToken(), getString(R.string.facebook_app_id), fbAccessToken.getUserId(), null, null, null, null, null);
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
