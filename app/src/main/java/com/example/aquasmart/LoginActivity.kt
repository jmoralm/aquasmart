package com.example.aquasmart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aquasmart.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Clase LoginActivity que representa la pantalla de inicio de sesión
 * de la aplicación Aquasmart
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var intent: Intent
    private lateinit var auth : FirebaseAuth

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

        auth = Firebase.auth
        initEvent()

    }

    /**
     * Inicializa los eventos del activity, incluyendo el listener del botón del login
     */
    private fun initEvent() {

        binding.tvOlvidoContrasena.setOnClickListener {
            resetPassword()
        }

        binding.buttonLogin.setOnClickListener {

            userName = binding.textInputUsername.text.toString()
            password = binding.textInputPassword.text.toString()

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese email y contraseña", Toast.LENGTH_SHORT).show()
            }

            auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user?.isEmailVerified == true) {
                        // Almacena el estado de la sesión en SharedPreferences
                        val sharedPreferences = getSharedPreferences("session_prefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("is_logged_in", true)
                        editor.apply()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                    } else {
                        auth.signOut()
                        Toast.makeText(this, "Por favor, verifique su correo electrónico", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    var message = "Error desconocido"
                    try {
                        throw task.exception ?: Exception("Error desconocido")
                    } catch (e: FirebaseAuthInvalidUserException) {
                        message = "El usuario no existe o ha sido deshabilitado"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        message = "Contraseña incorrecta"
                    } catch (e: Exception) {
                        message = e.message.toString()
                    }
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvLinkCrearCuenta.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java )
            startActivity(intent)
        }
    }

    /**
     * Muestra un mensaje en un Toast en la pantalla.
     *
     * @param message Mensaje a mostrar.
     */
    private fun showMsg(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    /**
     * Método para resetear el password
     */
    private fun resetPassword() {

        val email = binding.textInputUsername.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "Escriba su Email", Toast.LENGTH_SHORT).show()
            return
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Hemos enviado un correo de recuperación, revise su bandeja de entrada", Toast.LENGTH_SHORT).show()
            } else {
                var message = "Error desconocido"
                try {
                    throw task.exception ?: Exception("Error desconocido")
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    message = "Formato de email incorrecto"
                } catch (e: Exception) {
                    message = e.message.toString()
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}