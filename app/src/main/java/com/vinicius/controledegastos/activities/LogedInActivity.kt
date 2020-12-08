package com.vinicius.controledegastos.activities

import adapter.DespesaAdapter
import adapter.ReceitaAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vinicius.controledegastos.R
import model.User
import repository.DespesaRepository
import repository.ReceitaRepository

class LogedInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var DB: FirebaseDatabase
    private var user: User? = null
    private lateinit var titulo: TextView
    private lateinit var ListaDeDespesas: RecyclerView
    private lateinit var ListaDeReceitas: RecyclerView
    private lateinit var CriarDespesa: Button
    private lateinit var CriarReceita: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        DB = Firebase.database
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loged_in)

        CriarReceita = findViewById(R.id.button_CriarReceita)
        CriarReceita.setOnClickListener {
            val it = Intent(this, CadastroReceitaActivity::class.java)
            startActivity(it)
        }

        CriarDespesa = findViewById(R.id.CriarDespesa)
        CriarDespesa.setOnClickListener {
            val it = Intent(this, CadastroDespesaActivity::class.java)
            startActivity(it)
        }


        val despesaRepo = DespesaRepository.findAll()
        val DespesaAdapter = DespesaAdapter(despesaRepo)
        val viewManager = LinearLayoutManager(this)
        ListaDeDespesas = findViewById<RecyclerView>(R.id.recyclerview_list).apply {
            adapter = DespesaAdapter
            layoutManager = viewManager
            hasFixedSize()
        }

        val receitaRepo = ReceitaRepository.findAll()
        val receitaAdapter = ReceitaAdapter(receitaRepo)
        val viewManager2 = LinearLayoutManager(this)
        ListaDeReceitas = findViewById<RecyclerView>(R.id.recyclerview_list2).apply {
            adapter = receitaAdapter
            layoutManager = viewManager2
            hasFixedSize()
        }

        titulo = findViewById(R.id.titulo)
        val userRef = DB.reference.child("user")
        userRef.orderByValue().addChildEventListener(object:ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                if(snapshot.key == auth.currentUser?.uid!!) {
                    user = snapshot.getValue(User::class.java)
                    Log.i("App", "User: ${user?.Nome}")
                    titulo.setText("Olá, ${user?.Nome}")
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}