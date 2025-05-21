package br.com.joanadarc.espacosaber.core.data.repository

import br.com.joanadarc.espacosaber.core.model.Aluno
import br.com.joanadarc.espacosaber.core.model.Sessao
import br.com.joanadarc.espacosaber.core.model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime
import javax.inject.Inject

interface SessaoRepository {
    fun obterSessoes(): Flow<List<Sessao>>
}

class SessaoRepositoryImpl @Inject constructor() : SessaoRepository {
    override fun obterSessoes(): Flow<List<Sessao>> = flowOf(
        listOf(
            Sessao(
                aluno = Aluno(nome = "Joana D'arc"),
                data = LocalDateTime.now(),
                status = Status.Agendado,
            ),
            Sessao(
                aluno = Aluno(nome = "Gabriela Angelo"),
                data = LocalDateTime.now(),
                status = Status.Cancelado,
            ),
            Sessao(
                aluno = Aluno(nome = "Joana D'arc"),
                data = LocalDateTime.now(),
                status = Status.Agendado,
            ),
            Sessao(
                aluno = Aluno(nome = "Gabriela Angelo"),
                data = LocalDateTime.now(),
                status = Status.Concluido,
            ),
            Sessao(
                aluno = Aluno(nome = "Joana D'arc"),
                data = LocalDateTime.now(),
                status = Status.Aberto,
            ),
        )
    )
}