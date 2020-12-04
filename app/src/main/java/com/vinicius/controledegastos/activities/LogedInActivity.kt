package com.vinicius.controledegastos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
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

class LogedInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var DB: FirebaseDatabase
    private var user: User? = null
    private lateinit var titulo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        DB = Firebase.database
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loged_in)

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
        Log.i("App","UID: ${auth.currentUser?.uid!!}")

    }
}