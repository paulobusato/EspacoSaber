package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Endereco
import br.com.joanadarc.espacosaber.core.model.Responsavel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface ResponsavelRepository {
    fun obterResponsavel(responsavelId: String): Flow<Responsavel>
}

class ResponsavelRepositoryImpl @Inject constructor() : ResponsavelRepository {
    override fun obterResponsavel(responsavelId: String) = flowOf(
        Responsavel(
            nome = "João Silva",
            cpf = "987.654.321-00",
            dataNascimento = "05/05/1980",
            nacionalidade = "Brasileira",
            telefone = "(11) 88888-8888",
            email = "james.madison@examplepetstore.com",
            rg = "98.765.432-1",
            endereco = Endereco(
                logradouro = "Rua Exemplo",
                numero = "456",
                complemento = "Casa 2",
                bairro = "Centro",
                cep = "54321-876",
                cidade = "São Paulo",
                estado = "SP",
            ),
            cidadeNascimento = "São Paulo",
        ),
    )
}