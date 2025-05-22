package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Bairro
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface BairroRepository {
    fun obterBairros(): Flow<List<Bairro>>
}

class BairroRepositoryImpl @Inject constructor() : BairroRepository {
    override fun obterBairros(): Flow<List<Bairro>> = flowOf(
        listOf(
            Bairro(nome = "Jardim Itapemirim"),
            Bairro(nome = "BNH"),
            Bairro(nome = "Gilberto Machado"),
        )
    )
}