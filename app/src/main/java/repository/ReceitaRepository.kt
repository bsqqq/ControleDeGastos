package repository

import model.ReceitaModel

object ReceitaRepository {
    fun findAll():List<ReceitaModel> {
        return listOf(
            ReceitaModel("Salario","05/06/2020","600"),
            ReceitaModel("Transferência Recebida","16/06/2020","100"),
            ReceitaModel("Venda","22/06","1400"),
            ReceitaModel("Salario","05/07/2020","600"),
            ReceitaModel("Auxilio Paletó","10/07/2020","50000"),
            ReceitaModel("PicPay","22/07/2020","10"),
        )
    }
}