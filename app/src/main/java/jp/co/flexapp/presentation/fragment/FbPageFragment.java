package jp.co.flexapp.presentation.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.FacebookSdk;

import jp.co.flexapp.R;
import jp.co.flexapp.presentation.activity.FbOAuthActivity;

public class FbPageFragment extends BasePageFragment {

    private OnFragmentInteractionListener mListener;

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
        Button toLoginBtn = (Button) view.findViewById(R.id.fb_login_btn);
        toLoginBtn.setOnClickListener(v -> {
            startActivity(FbOAuthActivity.makeIntent(getActivity()));
        });
        return view;
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
