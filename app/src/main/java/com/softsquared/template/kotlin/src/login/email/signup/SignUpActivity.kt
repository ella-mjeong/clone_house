package com.softsquared.template.kotlin.src.login.email.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivitySignUpBinding
import com.softsquared.template.kotlin.src.login.editor
import com.softsquared.template.kotlin.src.login.email.signup.models.PostSignUp
import com.softsquared.template.kotlin.src.login.email.signup.models.ResultSignUp
import com.softsquared.template.kotlin.src.main.MainActivity
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate), SignUpActivityView {
    var emailCheck = false
    var pwCheck = false
    var pwRepeatCheck = false
    var nicknameCheck = false
    var agreeCheck = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.cbAll.setOnClickListener {
            if(binding.cbAll.isChecked){
                binding.cbAge.isChecked = true
                binding.cbAgree.isChecked = true
                binding.cbEvent.isChecked = true
                binding.cbService.isChecked = true
                binding.txtRedAgree.visibility = View.GONE
                binding.bdAgree.setBackgroundResource(R.drawable.white_edit_text)
                agreeCheck = true
                if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                    binding.btnSignupComplete.isEnabled = true
                    binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                }
                else{
                    binding.btnSignupComplete.isEnabled = false
                }
            }
            else{
                binding.cbAge.isChecked = false
                binding.cbAgree.isChecked = false
                binding.cbEvent.isChecked = false
                binding.cbService.isChecked = false
                binding.txtRedAgree.visibility = View.VISIBLE
                binding.bdAgree.setBackgroundResource(R.drawable.red_edit_text)
                agreeCheck = false
                binding.btnSignupComplete.isEnabled = false
            }
        }

        binding.cbAge.setOnClickListener {
            if(binding.cbAge.isChecked and binding.cbAgree.isChecked and binding.cbService.isChecked){
                binding.txtRedAgree.visibility = View.GONE
                binding.bdAgree.setBackgroundResource(R.drawable.white_edit_text)
                agreeCheck = true
                if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                    binding.btnSignupComplete.isEnabled = true
                    binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                }
                else{
                    binding.btnSignupComplete.isEnabled = false
                }
            }
            else{
                binding.txtRedAgree.visibility = View.VISIBLE
                binding.bdAgree.setBackgroundResource(R.drawable.red_edit_text)
                agreeCheck = false
                binding.btnSignupComplete.isEnabled = false
            }
        }
        binding.cbAgree.setOnClickListener {
            if(binding.cbAge.isChecked and binding.cbAgree.isChecked and binding.cbService.isChecked){
                binding.txtRedAgree.visibility = View.GONE
                binding.bdAgree.setBackgroundResource(R.drawable.white_edit_text)
                agreeCheck = true
                if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                    binding.btnSignupComplete.isEnabled = true
                    binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                }
                else{
                    binding.btnSignupComplete.isEnabled = false
                }
            }
            else{
                binding.txtRedAgree.visibility = View.VISIBLE
                binding.bdAgree.setBackgroundResource(R.drawable.red_edit_text)
                agreeCheck = false
                binding.btnSignupComplete.isEnabled = false
            }
        }
        binding.cbService.setOnClickListener {
            if(binding.cbAge.isChecked and binding.cbAgree.isChecked and binding.cbService.isChecked){
                binding.txtRedAgree.visibility = View.GONE
                binding.bdAgree.setBackgroundResource(R.drawable.white_edit_text)
                agreeCheck = true
                if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                    binding.btnSignupComplete.isEnabled = true
                    binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                }
                else{
                    binding.btnSignupComplete.isEnabled = false
                }
            }
            else{
                binding.txtRedAgree.visibility = View.VISIBLE
                binding.bdAgree.setBackgroundResource(R.drawable.red_edit_text)
                agreeCheck = false
                binding.btnSignupComplete.isEnabled = false
            }
        }


        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val ps:Pattern = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")

                if (!ps.matcher(binding.edtEmail.text.toString()).matches()) {
                    if (binding.edtEmail.text.toString().isEmpty()) {
                        binding.txtRedEmail.visibility = View.VISIBLE
                        binding.txtRedEmail.text = getString(R.string.sign_up_txt_hint_email_warn)
                        binding.bdEmail.setBackgroundResource(R.drawable.red_edit_text)
                        emailCheck = false
                        binding.btnSignupComplete.isEnabled = false
                    }
                    else {
                        binding.txtRedEmail.visibility = View.VISIBLE
                        binding.txtRedEmail.text = getString(R.string.sign_up_txt_hint_email_warn2)
                        binding.bdEmail.setBackgroundResource(R.drawable.red_edit_text)
                        emailCheck = false
                        binding.btnSignupComplete.isEnabled = false
                    }
                }
                else{
                    binding.txtRedEmail.visibility = View.GONE
                    binding.bdEmail.setBackgroundResource(R.drawable.white_edit_text)
                    emailCheck = true
                    if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                        binding.btnSignupComplete.isEnabled = true
                        binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                    }
                    else{
                        binding.btnSignupComplete.isEnabled = false
                    }
                }
            }
        })

        binding.edtPw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.edtPw.length() < 8) {
                    binding.txtRedPw.visibility = View.VISIBLE
                    binding.bdPw.setBackgroundResource(R.drawable.red_edit_text)
                    pwCheck = false
                    binding.btnSignupComplete.isEnabled = false
                }
                else{
                    binding.txtRedPw.visibility = View.GONE
                    binding.bdPw.setBackgroundResource(R.drawable.white_edit_text)
                    pwCheck = true
                    if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                        binding.btnSignupComplete.isEnabled = true
                        binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                    }
                    else{
                        binding.btnSignupComplete.isEnabled = false
                    }
                }
            }
        })
        binding.edtPwRepeat.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.edtPwRepeat.text.toString().isEmpty()) {
                    binding.txtRedPwRepeat.visibility = View.VISIBLE
                    binding.txtRedPwRepeat.text = getString(R.string.sign_up_txt_hint_pw_repeat_warn)
                    binding.bdPwRepeat.setBackgroundResource(R.drawable.red_edit_text)
                    pwRepeatCheck = false
                    binding.btnSignupComplete.isEnabled = false
                }
                else if (binding.edtPw.text.toString() != binding.edtPwRepeat.text.toString()) {
                    binding.txtRedPwRepeat.visibility = View.VISIBLE
                    binding.txtRedPwRepeat.text = getString(R.string.sign_up_txt_hint_pw_repeat_warn2)
                    binding.bdPwRepeat.setBackgroundResource(R.drawable.red_edit_text)
                    pwRepeatCheck = false
                    binding.btnSignupComplete.isEnabled = false
                }
                else{
                    binding.txtRedPwRepeat.visibility = View.GONE
                    binding.bdPwRepeat.setBackgroundResource(R.drawable.white_edit_text)
                    pwRepeatCheck = true
                    if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                        binding.btnSignupComplete.isEnabled = true
                        binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                    }
                    else{
                        binding.btnSignupComplete.isEnabled = false
                    }
                }
            }
        })
        binding.edtNickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((binding.edtNickname.length() < 2) or (binding.edtNickname.length() > 15)) {
                    binding.txtRedNickname.visibility = View.VISIBLE
                    binding.bdNickname.setBackgroundResource(R.drawable.red_edit_text)
                    binding.btnSignupComplete.isEnabled = false
                    nicknameCheck = false
                }
                else{
                    binding.txtRedNickname.visibility = View.GONE
                    binding.bdNickname.setBackgroundResource(R.drawable.white_edit_text)
                    nicknameCheck = true
                    if(emailCheck and pwCheck and pwRepeatCheck and nicknameCheck and agreeCheck) {
                        binding.btnSignupComplete.isEnabled = true
                        binding.btnSignupComplete.setBackgroundResource(R.color.colorMainBlue)
                    }
                    else{
                        binding.btnSignupComplete.isEnabled = false
                    }
                }
            }
        })

        binding.btnSignupComplete.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPw.text.toString()
            val nickname = binding.edtNickname.text.toString()
            val postRequest = PostSignUp(email = email, password = password, nickname = nickname)
            //showLoadingDialog(context!!)
            SignUpService(this).tryPostSignUp(postRequest) //서비스를 만들 떄 뷰를 넘겨준다
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onPostSignUpSuccess(response: ResultSignUp) {
        //dismissLoadingDialog()
        //binding.homeBtnTryPostHttpMethod.text = response.message

        if(response.code == 1000) {
            finish()
        }
    }

    override fun onPostSignUpFailure(message: String) {
       // dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

}
