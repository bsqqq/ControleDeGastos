package repository

import model.DespesaModel

object DespesaRepository {
    fun findAll():List<DespesaModel> {
        return listOf(
                DespesaModel("Cabelereiro", "27/08/1999", "Corte de cabelo", "30"),
                DespesaModel("Farmacia", "12/01/2001", "Remedio para gripe", "27.90"),
                DespesaModel("Mecanico", "12/09/2002", "Troca de pneu", "300"),
                DespesaModel("Petshop", "17/03/2010", "Ração", "70"),
                DespesaModel("Posto de Gasolina", "27/08/1999", "Troca de oleo e abastecimento", "160")
        )
    }
}