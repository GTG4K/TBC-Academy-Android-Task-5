package com.example.tbcacademyandroidtask5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tbcacademyandroidtask5.Util.Validate
import com.example.tbcacademyandroidtask5.databinding.ActivityLoginBinding
import com.example.tbcacademyandroidtask5.databinding.ActivityRegister2Binding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityRegister2Binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val validate = Validate()

        val receivedEmail = intent.getStringExtra("email")
        val receivedPassword = intent.getStringExtra("password")
        val username = binding.etUsername
        val usernameError = binding.tvUsernameError

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            receivedEmail?.let { email ->
                receivedPassword?.let { password ->

                    clearErrors()
                    validate.validationPassed = true

                    if (!validate.valueFilled(username.text.toString())) {
                        usernameError.text = "Username field is required"
                        username.setBackgroundResource(R.drawable.et_white_error)
                    }

                    if(validate.validationPassed) register(email, password);
                }
            }
        }
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val homeActivity = Intent(this, HomeActivity::class.java)
                    startActivity(homeActivity)
                    finish()
                } else {
                    Toast.makeText(baseContext, "Registration failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun clearErrors() {
        binding.tvUsernameError.text = ""
        binding.etUsername.setBackgroundResource(R.drawable.et_white)
    }
}