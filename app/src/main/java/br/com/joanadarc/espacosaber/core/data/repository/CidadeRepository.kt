package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Cidade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface CidadeRepository {
    fun obterCidades(): Flow<List<Cidade>>
}

class CidadeRepositoryImpl @Inject constructor() : CidadeRepository {
    override fun obterCidades(): Flow<List<Cidade>> = flowOf(
        listOf(
            Cidade(nome = "São Paulo"),
            Cidade(nome = "Rio de Janeiro"),
            Cidade(nome = "Belo Horizonte"),
        )
    )
}