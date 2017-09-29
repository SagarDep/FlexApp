package jp.co.flexapp.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jp.co.flexapp.R
import jp.co.flexapp.common.enums.TabType
import jp.co.flexapp.infla.db.FbAccessToken
import jp.co.flexapp.presentation.FlexApp

class FbOAuthActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    private var disposable: Disposable? = null

    internal var callback: FacebookCallback<LoginResult> = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            //内部ストレージに格納(Room)
            var accessToken: AccessToken = loginResult.accessToken
            var disposable: Disposable = putFbTokenObservable(accessToken)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            startActivity(MainActivity.makeIntent(this@FbOAuthActivity).putExtra("TAB_TYPE", TabType.FACEBOOK.value))
            finish()
        }

        override fun onCancel() {}

        override fun onError(error: FacebookException) {
            error.printStackTrace()
        }
    }

    private fun putFbTokenObservable(accessToken: AccessToken): Observable<Void> {
        return Observable.create {
            subscriber ->

            var fbAccessToken = FbAccessToken(accessToken.token, accessToken.userId)
            FlexApp.get().db.fbAccessTokenDao().putFbToken(fbAccessToken)
            subscriber.onComplete()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(this)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager, callback)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    companion object {
        @JvmStatic fun makeIntent(context: Context): Intent {
            val intent: Intent = Intent(context, FbOAuthActivity::class.java)
            return intent
        }
    }
}
