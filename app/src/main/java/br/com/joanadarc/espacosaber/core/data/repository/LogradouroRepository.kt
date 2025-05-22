package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Logradouro
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface LogradouroRepository {
    fun obterLogradouros(): Flow<List<Logradouro>>
}

class LogradouroRepositoryImpl @Inject constructor() : LogradouroRepository {
    override fun obterLogradouros(): Flow<List<Logradouro>> = flowOf(
        listOf(
            Logradouro(nome = "Rua Exemplo"),
            Logradouro(nome = "Avenida Principal"),
            Logradouro(nome = "Praça Central"),
        )
    )
}