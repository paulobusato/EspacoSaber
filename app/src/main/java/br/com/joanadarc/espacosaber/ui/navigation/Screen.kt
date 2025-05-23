package br.com.joanadarc.espacosaber.ui.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute


@Serializable
object AlunoRoute

@Serializable
object EditAlunoRoute

fun NavController.navigateToAlunos() = navigate(route = AlunoRoute)
fun NavController.navigateToEditAluno() = navigate(route = EditAlunoRoute)


@Serializable
object EditResponsavelRoute


@Serializable
object SessaoRoute

@Serializable
object EditSessaoRoute

fun NavController.navigateToSessoes() = navigate(route = SessaoRoute)
fun NavController.navigateToEditSessao() = navigate(route = EditSessaoRoute)