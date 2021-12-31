package com.softsquared.template.kotlin.src.login

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.sSharedPreferences
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.src.login.email.EmailActivity
import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import com.softsquared.template.kotlin.src.login.kakao.KakaoLoginActivityView
import com.softsquared.template.kotlin.src.login.kakao.KakaoLoginService
import com.softsquared.template.kotlin.src.login.kakao.models.PostKakaoSignIn
import com.softsquared.template.kotlin.src.main.MainActivity

var isLogin: Boolean = false


lateinit var editor: SharedPreferences.Editor

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), KakaoLoginActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        startActivity(Intent(this, MainActivity::class.java))
//        finish()

        //글씨 크기 변경(최대 20,000원)
        val content = binding.txtMax.text
        val spannableString = SpannableString(content)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 5 , 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtMax.text = content

        var keyHash = Utility.getKeyHash(this)
        Log.d("KEY_HASH", keyHash)
        //Kakao SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_app_key))

        // 로그인 조합 예제
        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Toast.makeText(this,"아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                Log.e("LoginKakaoActivity", "로그인 실패", error)
            }
            else if (token != null) {
                Toast.makeText(this,"로그인 되었습니다", Toast.LENGTH_SHORT).show()
                isLogin = true
                editor.putString("state", isLogin.toString())
                editor.commit()
                Log.i("LoginKakaoActivity", "로그인 성공\n ${token.accessToken}\n")

            }
            updateKakaoLoginUi()
        }

        binding.btnKakaoSignUp.setOnClickListener {
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            LoginClient.instance.run {
                if (isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    loginWithKakaoAccount(this@LoginActivity, callback = callback)
                } else loginWithKakaoAccount(this@LoginActivity, callback = callback)
            }
            updateKakaoLoginUi()
        }

        binding.btnEmail.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateKakaoLoginUi()
    }

    fun updateKakaoLoginUi(){
        UserApiClient.instance.me() { user: User?, throwable: Throwable? ->
            if (user != null) { //로그인이 된 상태인지 아닌지 확인가능
                //로그인되었다면
                Log.i("LoginKakaoActivity", "invoke: id = " + user.id)
                Log.i("LoginKakaoActivity", "invoke: email = " + user.kakaoAccount!!.email)
                Log.i("LoginKakaoActivity", "invoke: gender = " + user.kakaoAccount!!.gender)
                Log.i("LoginKakaoActivity", "invoke: age = " + user.kakaoAccount!!.ageRange)

                //카카오 로그인에 성공했을 때
                val email = user.kakaoAccount!!.email
                val password = "kakao"
                val nickname = user.kakaoAccount!!.profile
                val postRequest = email?.let { PostKakaoSignIn(email = it, password = password, nickname = nickname) }
                //showLoadingDialog(context!!)
                if (postRequest != null) {
                    KakaoLoginService(this).tryPostKakaoSignIn(postRequest)
                } //서비스를 만들 떄 뷰를 넘겨준다
                Log.d("LoginKakaoActivity", "postRequest is Null")
            }
        }
    }

    override fun onPostKakaoSignInSuccess(response: ResultSignIn) {
        //dismissLoadingDialog()
        //binding.homeBtnTryPostHttpMethod.text = response.message
        //response.message?.let { showCustomToast(it) }

        if(response.code == 1000) {
            response.userInfo.email?.let { Log.d("LoginKakaoActivity", it) }
            Log.d("LoginKakaoActivity", response.jwt)

            editor.putString("X-ACCESS-TOKEN", response.jwt)
            editor.commit()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onPostKakaoSignInFailure(message: String) {
        //dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}
