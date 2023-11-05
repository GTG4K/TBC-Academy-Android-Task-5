package com.example.tbcacademyandroidtask5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tbcacademyandroidtask5.Util.Validate
import com.example.tbcacademyandroidtask5.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.etEmail
        val password = binding.etPassword
        val emailError = binding.tvEmailError
        val passwordError = binding.tvPasswordError

        val validate = Validate()

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnRegister.setOnClickListener{
            clearErrors()

            validate.validationPassed = true
            if (!validate.isEmail(email.text.toString())) {
                emailError.text = "Email is not formatted correctly"
                email.setBackgroundResource(R.drawable.et_white_error)
            }
            if (!validate.valueFilled(email.text.toString())) {
                emailError.text = "Email field is required"
                email.setBackgroundResource(R.drawable.et_white_error)
            }
            if (!validate.valueFilled(password.text.toString())) {
                passwordError.text = "Password field is required"
                password.setBackgroundResource(R.drawable.et_white_error)
            }

            if(validate.validationPassed){
                nextStep(email.text.toString(), password.text.toString())
            }
        }
    }

    private fun nextStep(email: String, password: String){
        val registerActivity2 = Intent(this, RegisterActivity2::class.java).apply {
            putExtra("email", email)
            putExtra("password", password)
        }
        startActivity(registerActivity2)
    }

    private fun clearErrors() {
        binding.tvEmailError.text = ""
        binding.etEmail.setBackgroundResource(R.drawable.et_white)
        binding.tvPasswordError.text = ""
        binding.etPassword.setBackgroundResource(R.drawable.et_white)
    }
}