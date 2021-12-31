package com.softsquared.template.kotlin.src.login.email

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityEmailBinding
import com.softsquared.template.kotlin.src.login.email.signin.EmailLoginActivity
import com.softsquared.template.kotlin.src.login.email.signup.SignUpActivity

class EmailActivity: BaseActivity<ActivityEmailBinding>(ActivityEmailBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.btnEmailSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        binding.btnEmailLogin.setOnClickListener {
            startActivity(Intent(this, EmailLoginActivity::class.java))
            finish()
        }
    }
}