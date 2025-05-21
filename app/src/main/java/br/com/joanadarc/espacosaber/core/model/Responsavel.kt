package br.com.joanadarc.espacosaber.core.model

data class Responsavel(
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val nacionalidade: String,
    val telefone: String,
    val email: String,
    val rg: String,
    val endereco: Endereco,
    val cidadeNascimento: String,
)