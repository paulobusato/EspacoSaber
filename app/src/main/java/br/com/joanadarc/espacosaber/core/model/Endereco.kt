package br.com.joanadarc.espacosaber.core.model

data class Endereco (
    val logradouro: String,
    val numero: String,
    val complemento: String,
    val bairro: String,
    val cep: String,
    val cidade: String,
    val estado: String,
)