package br.com.joanadarc.espacosaber.core.model

import java.time.LocalDateTime

enum class Status(val descricao: String, val cor: Long) {
    Agendado(descricao = "Agendado", cor = 0xFF000000),
    Aberto(descricao = "Aberto", cor = 0xFF0B7C29),
    Concluido(descricao = "Concluído", cor = 0xFF1C62FA),
    Cancelado(descricao = "Cancelado", cor = 0xFFC41B1B),
}

enum class Procedimento(val descricao: String) {
    Diagnostico(descricao = "Diagnóstico"),
    Tratamento(descricao = "Tratamento"),
    Prognostico(descricao = "Prognóstico"),
    Intervencao(descricao = "Intervenção"),
    Avaliacao(descricao = "Avaliação"),
}

data class Sessao(
    val queixa: String? = null,
    val encaminhamento: String? = null,
    val atividade: Atividade? = null,
    val observacao: String? = null,
    val data: LocalDateTime? = null,
    val status: Status? = null,
    val procedimento: Procedimento? = null,
    val aluno: Aluno? = null,
)
