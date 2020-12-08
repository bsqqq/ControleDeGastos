package com.vinicius.controledegastos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vinicius.controledegastos.R
import model.ReceitaModel

class CadastroReceitaActivity : AppCompatActivity() {
    private lateinit var nomeDoReceita: EditText
    private lateinit var dataDoReceita: EditText
    private lateinit var valorDoReceita: EditText
    private lateinit var cadastroReceita: Button
    private var RTDB: FirebaseDatabase = Firebase.database
    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_receita)

        nomeDoReceita = findViewById(R.id.input_nomeDaReceita)
        dataDoReceita = findViewById(R.id.input_DataDaReceita)
        valorDoReceita = findViewById(R.id.input_ValorDaReceita)
        cadastroReceita = findViewById(R.id.button_CriarReceita)
        var formOk = true

        cadastroReceita.setOnClickListener {
            if (nomeDoReceita.text.isEmpty()) {
                nomeDoReceita.error = "Por favor, preencha este campo."
                formOk = false
            }
            if (dataDoReceita.text.isEmpty()) {
                dataDoReceita.error = "Por favor, preencha este campo."
                formOk = false
            }
            if (valorDoReceita.text.isEmpty()) {
                valorDoReceita.error = "Por favor, preencha este campo."
                formOk = false
            }

            if (formOk) {
                val nome_da_receita = nomeDoReceita.text.toString()
                val data_da_receita = dataDoReceita.text.toString()
                val valor_da_receita = valorDoReceita.text.toString()
                val path = RTDB.reference.child("Receita")
                path.child("${auth.currentUser?.uid}").child(data_da_receita).setValue(ReceitaModel(nome_da_receita, data_da_receita, valor_da_receita))
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Receita criada com sucesso.", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Por favor verifique por erros no formulario acima, ou contate o desenvolvedor", Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        }
    }
}