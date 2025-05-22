package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Nacionalidade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface NacionalidadeRepository {
    fun obterNacionalidades(): Flow<List<Nacionalidade>>
}

class NacionalidadeRepositoryImpl @Inject constructor() : NacionalidadeRepository {
    override fun obterNacionalidades(): Flow<List<Nacionalidade>> = flowOf(
        listOf(
            Nacionalidade(nome = "Brasileira"),
            Nacionalidade(nome = "Italiana"),
            Nacionalidade(nome = "Francesa"),
        )
    )
}