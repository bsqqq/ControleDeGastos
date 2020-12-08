package com.vinicius.controledegastos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vinicius.controledegastos.R
import model.DespesaModel

class CadastroDespesaActivity : AppCompatActivity() {
    private lateinit var NomeDaDespesa: EditText
    private lateinit var DataDaDespesa: EditText
    private lateinit var DescricaoDaDespesa: EditText
    private lateinit var ValorDaDespesa: EditText
    private lateinit var BotaoCadastroDespesa: Button
    private var RTDB: FirebaseDatabase = Firebase.database
    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_despesa)

        NomeDaDespesa = findViewById(R.id.input_nomeDaDespesa)
        DataDaDespesa = findViewById(R.id.input_DataDaDespesa)
        DescricaoDaDespesa = findViewById(R.id.input_DescricaoDaDespesa)
        ValorDaDespesa = findViewById(R.id.input_ValorDaDespesa)
        BotaoCadastroDespesa = findViewById(R.id.button_CadastrarDespesa)
        var formOk = true

        BotaoCadastroDespesa.setOnClickListener {
            if (NomeDaDespesa.text.isEmpty()) {
                NomeDaDespesa.error = "Por favor preencha este campo."
                formOk = false
            }

            if (DataDaDespesa.text.isEmpty()) {
                DataDaDespesa.error = "Por favor preencha este campo."
                formOk = false
            }

            if (DescricaoDaDespesa.text.isEmpty()) {
                DescricaoDaDespesa.error = "Por favor preencha este campo."
                formOk = false
            }

            if (ValorDaDespesa.text.isEmpty()) {
                ValorDaDespesa.error = "Por favor preencha este campo."
                formOk = false
            }

            if (formOk) {
                val Nome_Da_Despesa = NomeDaDespesa.text.toString()
                val Data_Da_Despesa = DataDaDespesa.text.toString()
                val Descricao_Da_Despesa = DescricaoDaDespesa.text.toString()
                val Valor_Da_Despesa = ValorDaDespesa.text.toString()
                val path = RTDB.reference.child("Despesa")
                    path.child("${auth.currentUser?.uid!!}").child(Data_Da_Despesa).setValue(DespesaModel(Nome_Da_Despesa, Data_Da_Despesa, Descricao_Da_Despesa, Valor_Da_Despesa))
                            .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Despesa criada com sucesso.", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Por favor verifique por erros no formulario acima, ou contate o desenvolvedor", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}