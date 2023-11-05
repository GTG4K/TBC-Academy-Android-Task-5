package com.example.tbcacademyandroidtask5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tbcacademyandroidtask5.Util.Validate
import com.example.tbcacademyandroidtask5.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = binding.etEmail
        val password = binding.etPassword
        val emailError = binding.tvEmailError
        val passwordError = binding.tvPasswordError

        val validate = Validate()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnLogin.setOnClickListener {
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

            if (validate.validationPassed) signIn(email.text.toString(), password.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val homeActivity = Intent(this, HomeActivity::class.java)
                    startActivity(homeActivity)
                    finish()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun clearErrors() {
        binding.tvEmailError.text = ""
        binding.etEmail.setBackgroundResource(R.drawable.et_white)
        binding.tvPasswordError.text = ""
        binding.etPassword.setBackgroundResource(R.drawable.et_white)
    }
}