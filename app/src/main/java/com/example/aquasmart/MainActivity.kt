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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var controller: Controller
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

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

        auth = FirebaseAuth.getInstance()

        // Verifica si hay una sesión iniciada en SharedPreferences
        val sharedPreferences = getSharedPreferences("session_prefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        // Verifica si el usuario está autenticado
        val currentUser = auth.currentUser

        if (currentUser != null && currentUser.isEmailVerified && isLoggedIn) {
            // Usuario autenticado y sesión iniciada, inicia la UI principal
            initRecyclerView()
            init()

        } else {
            // No hay usuario autenticado o sesión no iniciada, redirige a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

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