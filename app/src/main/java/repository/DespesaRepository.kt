package repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import model.DespesaModel

//lateinit var despesaReferece: DatabaseReference
object DespesaRepository {
    fun findAll():List<DespesaModel> {
        return listOf(
                DespesaModel("Cabelereiro", "27/08/1999", "Corte de cabelo", "30"),
                DespesaModel("Farmacia", "12/01/2001", "Remedio para gripe", "27.90"),
                DespesaModel("Mecanico", "12/09/2002", "Troca de pneu", "300"),
                DespesaModel("Petshop", "17/03/2010", "Ração", "70"),
                DespesaModel("Posto de Gasolina", "27/08/1999", "Troca de oleo e abastecimento", "160")
        )
//        val database = Firebase.database
//        val myRef = database.reference
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val despesas = arrayListOf<DespesaModel>()
//                for (despesaSnapshot in snapshot.children) {
//                    val despesa = despesaSnapshot.getValue(DespesaModel::class.java)
//                    despesas.add(despesa!!)
//                }
//                return arrayListOf<List:DespesaModel>(despesa)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }
}}