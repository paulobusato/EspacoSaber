package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Estado
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface EstadoRepository {
    fun obterEstados(): Flow<List<Estado>>
}

class EstadoRepositoryImpl @Inject constructor() : EstadoRepository {
    override fun obterEstados(): Flow<List<Estado>> = flowOf(
        listOf(
            Estado(nome = "São Paulo"),
            Estado(nome = "Rio de Janeiro"),
            Estado(nome = "Belo Horizonte"),
        )
    )
}