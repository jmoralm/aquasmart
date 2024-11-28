package com.example.aquasmart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aquasmart.databinding.ActivityLoginBinding

/**
 * Clase LoginActivity que representa la pantalla de inicio de sesión
 * de la aplicación Aquasmart
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var intent: Intent

    /**
     * Constantes de inicio de sesión predeterminado
     */
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

    /**
     * Inicializa los eventos del activity, incluyendo el listener del botón del login
     */
    private fun initEvent() {

        binding.buttonLogin.setOnClickListener {

            userName = binding.textInputUsername.text.toString()
            password = binding.textInputPassword.text.toString()

            if (userName.isEmpty() || password.isEmpty() || userName.isBlank() || userName.isBlank()) {
                showMsg("Missing data")
            }
            if (loginVerify(userName, password)) {

                intent = Intent(this, MainActivity::class.java)
                intent.apply {
                    putExtra("username", "Jose Morillo")
                }
                startActivity(intent)
            }

        }
    }

    /**
     * Verifica si las credenciales ingresadas son correctas.
     *
     * @param userName Nombre de usuario ingresado.
     * @param password Contraseña ingresada.
     * @return `true` si las credenciales son válidas, de lo contrario `false`.
     */
    private fun loginVerify(userName: String, password: String): Boolean {
        return userNameVerify(userName) && password == PASSWORD
    }

    /**
     * Verifica si el nombre de usuario ingresado es válido.
     *
     * @param userName Nombre de usuario ingresado.
     * @return `true` si el nombre de usuario coincide con el predeterminado, de lo contrario `false`.
     */
    private fun userNameVerify(userName: String): Boolean {
        if (userName == USERNAME) {
            return true
        } else {
            showMsg("Username is not valid")
            return false
        }
    }

    /**
     * Verifica si la contraseña ingresada es válida.
     *
     * @param password Contraseña ingresada.
     * @return `true` si la contraseña coincide con la predeterminada, de lo contrario `false`.
     */
    private fun passwordVerify(password: String): Boolean {
        if (password == PASSWORD) {
            return true
        } else {
            showMsg("Password is not valid")
            return false
        }
    }

    /**
     * Muestra un mensaje en un Toast en la pantalla.
     *
     * @param message Mensaje a mostrar.
     */
    private fun showMsg(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}