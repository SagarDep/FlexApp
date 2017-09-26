package jp.co.flexapp.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import jp.co.flexapp.R
import jp.co.flexapp.common.enums.TabType

class FbOAuthActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    internal var callback: FacebookCallback<LoginResult> = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            //内部ストレージに格納(Room)

            startActivity(MainActivity.makeIntent(this@FbOAuthActivity).putExtra("TAB_TYPE", TabType.FACEBOOK.value))
            finish()
        }

        override fun onCancel() {}

        override fun onError(error: FacebookException) {
            error.printStackTrace()
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

    companion object {
        @JvmStatic fun makeIntent(context: Context): Intent {
            val intent: Intent = Intent(context, FbOAuthActivity::class.java)
            return intent
        }
    }
}
