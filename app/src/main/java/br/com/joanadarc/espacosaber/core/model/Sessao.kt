package br.com.joanadarc.espacosaber.core.model

import java.time.LocalDateTime

enum class Status(val descricao: String, val cor: Long) {
    Agendado(descricao = "Agendado", cor = 0xFF000000),
    Aberto(descricao = "Aberto", cor = 0xFF0B7C29),
    Concluido(descricao = "Concluído", cor = 0xFF1C62FA),
    Cancelado(descricao = "Cancelado", cor = 0xFFC41B1B),
}

data class Sessao(
    val aluno: Aluno,
    val data: LocalDateTime,
    val status: Status,
)
