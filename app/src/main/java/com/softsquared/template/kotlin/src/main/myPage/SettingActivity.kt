package com.softsquared.template.kotlin.src.main.myPage

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivitySettingBinding
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.login.editor
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.myPage.models.ResultLogOut

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate), LogOutActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnLogOut.setOnClickListener {
            LogOutService(this).tryGetLogOut()
        }
    }

    override fun onGetLogOutSuccess(response: ResultLogOut) {

        if(response.code == 1000) {

            editor.putString("X_ACCESS_TOKEN", null)
            editor.commit()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onGetLogOutFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}