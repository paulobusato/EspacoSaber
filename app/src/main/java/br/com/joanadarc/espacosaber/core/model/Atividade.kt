package br.com.joanadarc.espacosaber.core.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Atividade(
    val id: String? = null,
    val nome: String,
    val descricao: String,
    val categoria: String,
    val assunto: String,
    val area: String,
    val dataCadastro: LocalDate,
    val dataAtualizacao: LocalDateTime,
)