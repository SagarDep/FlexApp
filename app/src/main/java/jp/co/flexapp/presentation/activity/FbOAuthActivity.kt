package jp.co.flexapp.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import jp.co.flexapp.R
import jp.co.flexapp.common.enums.TabType
import java.net.URL

class FbOAuthActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private val accessTokenTracker: AccessTokenTracker? = null
    private val profileTracker: ProfileTracker? = null
    private var loginButton: LoginButton? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var birthday: String? = null
    private var gender: String? = null
    private var profilePicture: URL? = null
    private var userId: String? = null

    internal var callback: FacebookCallback<LoginResult> = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
//            val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
//                Log.e("FB", `object`.toString())
//                Log.e("FB", response.toString())
//
//                try {
//                    userId = `object`.getString("id")
//                    profilePicture = URL("https://graph.facebook.com/$userId/picture?width=500&height=500")
//                    if (`object`.has("first_name"))
//                        firstName = `object`.getString("first_name")
//                    if (`object`.has("last_name"))
//                        lastName = `object`.getString("last_name")
//                    if (`object`.has("email"))
//                        email = `object`.getString("email")
//                    if (`object`.has("birthday"))
//                        birthday = `object`.getString("birthday")
//                    if (`object`.has("gender"))
//                        gender = `object`.getString("gender")
//                    Log.d("hge", "hoge")
//
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                } catch (e: MalformedURLException) {
//                    e.printStackTrace()
//                }
//            }
//
//            val parameters = Bundle()
//            parameters.putString("fields", "id, first_name, last_name, email, birthday, gender")
//            request.parameters = parameters
//            request.executeAsync()
            startActivity(MainActivity.makeIntent(applicationContext).putExtra("TAB_TYPE", TabType.FACEBOOK.value))
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
