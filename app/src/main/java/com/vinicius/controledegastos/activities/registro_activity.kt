package com.vinicius.controledegastos.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vinicius.controledegastos.R
import model.User

class registro_activity: AppCompatActivity() {

    private lateinit var RegistroNome: EditText
    private lateinit var RegistroUsername: EditText
    private lateinit var RegistroEmail: EditText
    private lateinit var RegistroSenha: EditText
    private lateinit var RegistroConfirmarSenha: EditText
    private lateinit var BotaoRegistro: Button
    private lateinit var DB: FirebaseDatabase
    var formOk = true
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_activity)

        RegistroNome = findViewById(R.id.Registro_Nome)
        RegistroEmail = findViewById(R.id.Registro_Email)
        RegistroSenha = findViewById(R.id.Registro_Senha)
        RegistroConfirmarSenha = findViewById(R.id.Registro_ConfirmarSenha)
        BotaoRegistro = findViewById(R.id.Registro_Cadastrar)
        auth = Firebase.auth
        DB = Firebase.database

        BotaoRegistro.setOnClickListener {
            if (RegistroNome.text.isEmpty()) {
                RegistroNome.error = "O campo Nome está vazio, por favor preencha este campo."
                formOk = false
            }
            if (RegistroEmail.text.isEmpty()) {
                RegistroEmail.error = "O campo Email está vazio, por favor preencha este campo."
                formOk = false
            }
            if (RegistroSenha.text.isEmpty()) {
                RegistroSenha.error = "O campo Senha está vazio, por favor preencha este campo."
                formOk = false
            }
            if (RegistroSenha.length() < 6) {
                RegistroSenha.error = "O campo Senha não pode ter menos de 6 caracteres, por favor elabore uma senha mais forte."
            }
            if (RegistroConfirmarSenha.text.isEmpty()) {
                RegistroConfirmarSenha.error = "O campo Confirmar Senha está vazio, por favor preencha este campo."
                formOk = false
            }
            if (RegistroSenha.text.toString() != RegistroConfirmarSenha.text.toString()) {
                RegistroConfirmarSenha.error = "As senhas não estão iguais, por favor verifique novamente."
                formOk = false
            }
            if (formOk) {
                val Nome = RegistroNome.text.toString()
                val Email = RegistroEmail.text.toString()
                val Senha = RegistroSenha.text.toString()
                auth.createUserWithEmailAndPassword(Email, Senha)
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(registro_activity@this,
                                "Usuario cadastrado com sucesso. Entre com sua conta!",
                                Toast.LENGTH_SHORT)
                                .show()
                                val path = DB.reference.child("user")
                                path.child(auth.currentUser?.uid!!).setValue(User(Email, Nome, Senha))
                                finish()
                        } else {
                            Log.w("App", "signInWithEmail:failure", task.exception)
                            Toast.makeText(registro_activity@this,
                                "Erro ao criar usuario, por favor verifique por erros ou contate o desenvolvedor.",
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(registro_activity@this,
                    "Por favor verifique por erros nos campos acima.",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}