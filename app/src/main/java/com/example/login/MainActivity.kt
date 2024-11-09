package com.example.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var intent : Intent
    private lateinit var extraInfo: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getSetIntentInfo()
        initEvent()

    }

    private fun getSetIntentInfo() {
        intent = getIntent()
        extraInfo = intent.getStringExtra("username").toString()
        binding.textViewExtra.text = extraInfo
    }

    private fun initEvent() {
        binding.buttonCallCardView1.setOnClickListener {
            call("650490594")
        }
        binding.buttonCallCardView2.setOnClickListener {
            call("665434321")
        }
        binding.buttonEmailCardView1.setOnClickListener {
            goToGmail("jmoralm233@g.educaand.es")
        }
        binding.buttonEmailCardView2.setOnClickListener {
            goToGmail("santiago@gmail.com")
        }
    }

    private fun call(number :String) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:$number")
            }
            startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        }
    }

    private fun goToGmail(email: String) {
        intent = Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:"))
        intent.apply {
            putExtra(
                Intent.EXTRA_EMAIL,
                arrayOf(email)
            )
        }
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}