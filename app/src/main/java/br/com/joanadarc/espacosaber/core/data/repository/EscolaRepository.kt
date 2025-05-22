package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Escola
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface EscolaRepository {
    fun obterEscolas(): Flow<List<Escola>>
}

class EscolaRepositoryImpl @Inject constructor() : EscolaRepository {
    override fun obterEscolas(): Flow<List<Escola>> = flowOf(
        listOf(
            Escola(nome = "Escola Municipal"),
            Escola(nome = "Escola Estadual"),
        )
    )
}