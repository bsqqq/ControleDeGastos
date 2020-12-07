package repository

import model.Despesa

object DespesaRepository {
    fun findAll():List<Despesa> {
        return listOf(
                Despesa("Cabelereiro", "27/08/1999", "Corte de cabelo", 30),
                Despesa("Farmacia", "12/01/2001", "Remedio para gripe", 27.90),
                Despesa("Mecanico", "12/09/2002", "Troca de pneu", 300),
                Despesa("Petshop", "17/03/2010", "Ração", 70),
                Despesa("Posto de Gasolina", "27/08/1999", "Troca de oleo e abastecimento", 160)
        )
    }
}