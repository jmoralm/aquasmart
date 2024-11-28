package com.example.aquasmart

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aquasmart.controller.Controller
import com.example.aquasmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var controller : Controller
    lateinit var binding: ActivityMainBinding


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
        init()
        initRecyclerView()

    }

    /**
     * Método que iniciaciliza el controller y lanza el método setAdapter()
     */
    private fun init() {

        controller = Controller(this)
        controller.setAdapter()
    }

    /*
    Método que inicializa el recyclerView del Main Activity
     */
    private fun initRecyclerView() {
        binding.rvReports.layoutManager = LinearLayoutManager(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}