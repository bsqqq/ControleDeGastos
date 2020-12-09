package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.controledegastos.R
import model.ReceitaModel

class ReceitaAdapter(val receitaList:List<ReceitaModel>):RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    class ReceitaViewHolder(private val ReceitaView: View) : RecyclerView.ViewHolder(ReceitaView) {
        val receitaNome: TextView = ReceitaView.findViewById(R.id.nomeDoLucro)
        val receitaData: TextView = ReceitaView.findViewById(R.id.dataDoLucro)
        val receitaValor: TextView = ReceitaView.findViewById(R.id.valorDoLucro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val receitaView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout2, parent, false)
        return ReceitaViewHolder(receitaView)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val receita = receitaList[position]
        holder.receitaNome.text = receita.Nome_da_Receita
        holder.receitaData.text = receita.Data_da_Receita
        holder.receitaValor.text = receita.Valor_da_Receita
    }

    override fun getItemCount(): Int {
        return receitaList.size
    }
}