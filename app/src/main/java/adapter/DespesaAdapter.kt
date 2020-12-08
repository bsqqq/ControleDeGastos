package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.controledegastos.R
import model.DespesaModel

class DespesaAdapter(val DespesaList:List<DespesaModel>):RecyclerView.Adapter<DespesaAdapter.DespesaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DespesaViewHolder {
        val despesaView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DespesaViewHolder(despesaView)
    }

    override fun onBindViewHolder(holder: DespesaViewHolder, position: Int) {
        val despesa = DespesaList[position]
        holder.despesa_nome.text = despesa.Nome_da_Despesa
        holder.despesa_data.text = despesa.Data_da_despesa
        holder.despesa_valor.text = despesa.Valor_da_despesa
    }

    override fun getItemCount(): Int {
        return DespesaList.size
    }

    class DespesaViewHolder(val DespesaView: View):RecyclerView.ViewHolder(DespesaView) {
        val despesa_nome: TextView = DespesaView.findViewById(R.id.despesa_nome)
        val despesa_data: TextView = DespesaView.findViewById(R.id.despesa_data)
        val despesa_valor: TextView = DespesaView.findViewById(R.id.despesa_valor)
    }
}