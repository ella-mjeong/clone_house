package com.softsquared.template.kotlin.src.login.email.signin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityEmailLoginBinding
import com.softsquared.template.kotlin.src.login.email.pwreset.EmailPasswordResetActivity
import com.softsquared.template.kotlin.src.login.email.signin.models.PostSignIn
import com.softsquared.template.kotlin.src.login.editor
import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import com.softsquared.template.kotlin.src.main.MainActivity

class EmailLoginActivity: BaseActivity<ActivityEmailLoginBinding>(ActivityEmailLoginBinding::inflate), LoginActivityView {

    var emailCheck = false
    var pwCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.txtPwReset.setOnClickListener {
            startActivity(Intent(this, EmailPasswordResetActivity::class.java))
        }

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.edtEmail.length() > 0) {
                    emailCheck = true
                    if(pwCheck) {
                        binding.btnLogin.isEnabled = true
                        binding.btnLogin.setBackgroundResource(R.color.colorMainBlue)
                    }
                    else{
                        binding.btnLogin.isEnabled = false
                    }
                }
                else{
                    binding.btnLogin.isEnabled = false
                }
            }
        })
        binding.edtPw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.edtPw.length() > 0) {
                    pwCheck = true
                    if(emailCheck) {
                        binding.btnLogin.isEnabled = true
                        binding.btnLogin.setBackgroundResource(R.color.colorMainBlue)
                    }
                    else{
                        binding.btnLogin.isEnabled = false
                    }
                }
                else{
                    binding.btnLogin.isEnabled = false
                }
            }
        })

        binding.txtPwReset.setOnClickListener {
            startActivity(Intent(this, EmailPasswordResetActivity::class.java))
        }

        //로그인 버튼 눌렀을 때
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPw.text.toString()
            val postRequest = PostSignIn(email = email, password = password)
            //showLoadingDialog(context!!)
           LoginService(this).tryPostSignIn(postRequest) //서비스를 만들 떄 뷰를 넘겨준다
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onPostSignInSuccess(response: ResultSignIn) {
        //dismissLoadingDialog()
        //binding.homeBtnTryPostHttpMethod.text = response.message

        if(response.code == 1000) {
            response.userInfo.email?.let { Log.d("LoginActivity", it) }
            Log.d("LoginActivity", response.jwt)

            editor.putString("X_ACCESS_TOKEN", response.jwt)
            editor.commit()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onPostSignInFailure(message: String) {
        //dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}