package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.controledegastos.R
import model.ReceitaModel

class ReceitaAdapter(val ReceitaList:List<ReceitaModel>):RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val receitaView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout2, parent, false)
        return ReceitaViewHolder(receitaView)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val receita = ReceitaList[position]
        holder.nome.text = receita.Nome_da_Receita
        holder.data.text = receita.Data_da_Receita
        holder.valor.text = receita.Valor_da_Receita
    }

    override fun getItemCount(): Int {
        return ReceitaList.size
    }

    class ReceitaViewHolder(val ReceitaView: View):RecyclerView.ViewHolder(ReceitaView) {
        val nome: TextView = ReceitaView.findViewById(R.id.input_nomeDaReceita)
        val data: TextView = ReceitaView.findViewById(R.id.input_DataDaReceita)
        val valor: TextView = ReceitaView.findViewById(R.id.input_ValorDaReceita)
    }
}