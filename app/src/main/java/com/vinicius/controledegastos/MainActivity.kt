package com.vinicius.controledegastos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var entrar: Button
    private lateinit var cadastrar: Button
    private lateinit var LoginEmail: EditText
    private lateinit var LoginPassword: EditText
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = Firebase.auth
        LoginEmail = findViewById(R.id.Login_Email)
        LoginPassword = findViewById(R.id.Login_Password)

        entrar = findViewById(R.id.Entrar)
        entrar.setOnClickListener {
//            Log.i("Action", "Botão Entrar foi apertado")
            var formOk = true

            if (LoginEmail.text.isEmpty()) {
                LoginEmail.error = "Este campo não pode estar vazio, por favor preencha."
                formOk = false
            }

            if (LoginPassword.text.isEmpty()) {
                LoginPassword.error = "Este campo não pode estar vazio, por favor preencha."
                formOk = false
            }

            if (formOk) {
                mAuth.signInWithEmailAndPassword(LoginEmail.text.toString(), LoginPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                    } else {

                    }
                }
            }
        }

        cadastrar = findViewById(R.id.Cadastrar)
        cadastrar.setOnClickListener {
//            Log.i("Action", "Botão Cadastrar foi apertado")
            val it = Intent(this, registro_activity::class.java)
            startActivity(it)
        }
    }
}