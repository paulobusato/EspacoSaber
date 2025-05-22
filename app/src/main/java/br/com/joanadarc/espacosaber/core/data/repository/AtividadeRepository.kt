package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Atividade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

interface AtividadeRepository {
    fun obterAtividades(): Flow<List<Atividade>>
}

class AtividadeRepositoryImpl @Inject constructor() : AtividadeRepository {
    override fun obterAtividades(): Flow<List<Atividade>> = flowOf(
        listOf(
            Atividade(
                nome = "Atividade 1",
                descricao = "Descrição da atividade 1",
                categoria = "Categoria 1",
                assunto = "Assunto 1",
                area = "Área 1",
                dataCadastro = LocalDate.now(),
                dataAtualizacao = LocalDateTime.now(),
            ),
            Atividade(
                nome = "Atividade 1",
                descricao = "Descrição da atividade 1",
                categoria = "Categoria 1",
                assunto = "Assunto 1",
                area = "Área 1",
                dataCadastro = LocalDate.now(),
                dataAtualizacao = LocalDateTime.now(),
            ),
            Atividade(
                nome = "Atividade 1",
                descricao = "Descrição da atividade 1",
                categoria = "Categoria 1",
                assunto = "Assunto 1",
                area = "Área 1",
                dataCadastro = LocalDate.now(),
                dataAtualizacao = LocalDateTime.now(),
            ),
        )
    )
}