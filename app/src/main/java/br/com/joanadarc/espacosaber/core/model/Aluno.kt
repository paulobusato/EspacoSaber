package br.com.joanadarc.espacosaber.core.model

import java.time.LocalDate

data class Aluno(
    val nome: String? = null,
    val cpf: String? = null,
    val dataNascimento: String? = null,
    val nacionalidade: String? = null,
    val telefone: String? = null,
    val email: String? = null,
    val rg: String? = null,
    val endereco: Endereco? = null,
    val cidadeNascimento: String? = null,
    val responsavel: Responsavel? = null,
    val escola: String? = null,
    val serie: Int? = null,
    val turno: String? = null,
    val dataCadastro: LocalDate? = null,
    val dataAtualizacao: LocalDate? = null,
)
