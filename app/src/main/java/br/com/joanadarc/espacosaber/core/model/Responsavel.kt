package br.com.joanadarc.espacosaber.core.model

data class Responsavel(
    val id: String? = null,
    val nome: String? = null,
    val cpf: String? = null,
    val dataNascimento: String? = null,
    val nacionalidade: String? = null,
    val telefone: String? = null,
    val email: String? = null,
    val rg: String? = null,
    val endereco: Endereco? = null,
    val cidadeNascimento: String? = null,
)