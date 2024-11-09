package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userName : String
    private lateinit var password : String
    private lateinit var intent : Intent


    companion object {
        const val USERNAME = "josemorillo"
        const val PASSWORD = "admin"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initEvent()

    }

    private fun initEvent() {

        binding.buttonLogin.setOnClickListener {

            userName = binding.textInputUsername.text.toString()
            password = binding.textInputPassword.text.toString()

            if(userName.isEmpty() || password.isEmpty() || userName.isBlank() || userName.isBlank()){
                showMsg("Missing data")
            }
            if(loginVerify(userName, password)){

                intent = Intent(this, MainActivity::class.java)
                intent.apply {
                    putExtra("username", "Jose Morillo")
                }
                startActivity(intent)
            }

        }
    }

    private fun loginVerify(userName : String, password : String): Boolean {
        return userNameVerify(userName) && password == PASSWORD
    }

    private fun userNameVerify(userName: String) : Boolean{
        if(userName == USERNAME) {
            return true
        } else {
            showMsg("Username is not valid")
            return false
        }
    }

    private fun passwordVerify(password: String) : Boolean{
        if(password == PASSWORD) {
            return true
        } else {
            showMsg("Password is not valid")
            return false
        }
    }

    private fun showMsg(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}