package com.example.aquasmart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aquasmart.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding : ActivityRegisterBinding
    private lateinit var authentication : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(registerBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        authentication = FirebaseAuth.getInstance()

        initListener()

    }

    private fun initListener() {

        registerBinding.buttonRegister.setOnClickListener {

            val email = registerBinding.textInputEmail.text.toString()
            val pass = registerBinding.textInputPassword.text.toString()
            val repeatPass = registerBinding.textRepeatPassword.text.toString()

            if(!verifyIsNotEmptyOrNull(email, pass, repeatPass)){
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show()
            } else if (!verifyPass(pass, repeatPass)){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            } else {
                registerUser(email, pass)
            }

        }
    }

    private fun registerUser(email: String, pass: String) {
        authentication.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val user = authentication.currentUser
                user?.sendEmailVerification()?.addOnCompleteListener { emailtask ->
                    if(emailtask.isSuccessful) {
                        Toast.makeText(this, "Registrado con éxito, revise su correo", Toast.LENGTH_LONG).show()
                        authentication.signOut()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                handleRegistrationError(task.exception)
            }
        }
    }

    private fun handleRegistrationError(exception: Exception?) {
        try {
            throw exception ?: Exception("Error desconocido")
        } catch (e: FirebaseAuthUserCollisionException) {
            Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_LONG).show()
        } catch (e: FirebaseAuthWeakPasswordException) {
            Toast.makeText(this, "La contraseña es demasiado débil: ${e.reason}", Toast.LENGTH_LONG).show()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(this, "El email proporcionado no es válido", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun verifyPass(pass: String, pass2 : String) :Boolean {
        return pass == pass2
    }

    private fun verifyIsNotEmptyOrNull(email: String, pass: String, pass2: String) : Boolean {
        return email.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && pass2.isNotEmpty()
    }
}