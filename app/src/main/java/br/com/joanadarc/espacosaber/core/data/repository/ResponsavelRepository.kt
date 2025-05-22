package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Endereco
import br.com.joanadarc.espacosaber.core.model.Responsavel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface ResponsavelRepository {
    fun obterResponsavel(responsavelId: String): Flow<Responsavel>
    fun obterResponsaveis(): Flow<List<Responsavel>>
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

    override fun obterResponsaveis(): Flow<List<Responsavel>> = flowOf(
        listOf(
            Responsavel(
                nome = "João Silva",
                cpf = "987.654.321-00",
                dataNascimento = "05/05/1980",
                nacionalidade = "Brasileira",
                telefone = "(11) 88888-8888",
                email = "john.jay@example.com",
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
            Responsavel(
                nome = "Maria Santos",
                cpf = "123.456.789-00",
                dataNascimento = "15/03/1950",
                nacionalidade = "Brasileira",
                telefone = "(11) 77777-7777",
                email = "william.henry.harrison@example-pet-store.com",
                rg = "12.345.678-9",
                endereco = Endereco(
                    logradouro = "Rua Exemplo",
                    numero = "7",
                    complemento = "Casa 1",
                    bairro = "Centro",
                    cep = "98765-432",
                    cidade = "São Paulo",
                    estado = "SP",
                ),
                cidadeNascimento = "São Paulo",
            ),
        )
    )
}