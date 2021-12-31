package com.softsquared.template.kotlin.src.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.ApplicationClass.Companion.sSharedPreferences
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivitySplashSecondBinding
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.login.editor
import com.softsquared.template.kotlin.src.login.isLogin
import com.softsquared.template.kotlin.src.main.MainActivity

class SplashSecondActivity : BaseActivity<ActivitySplashSecondBinding>(ActivitySplashSecondBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // //로그인 했었는지 확인
         sSharedPreferences = getSharedPreferences("X-ACCESS-TOKEN", Activity.MODE_PRIVATE)
         editor = sSharedPreferences.edit()

       val jwt = sSharedPreferences.getString("X_ACCESS_TOKEN",null)

        //jwt값이 존재하면 바로 로그인할 수 있게끔(isLogin == true) 자동로그인 구현
        isLogin = jwt != null

        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin){
                Log.i("SplashActivity","로그인 확인됨")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 2000)
    }

}