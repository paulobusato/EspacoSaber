package br.com.joanadarc.espacosaber.ui.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute


@Serializable
object AlunoRoute

@Serializable
data class EditAlunoRoute(val id: String? = null)

fun NavController.navigateToAlunos() = navigate(route = AlunoRoute)
fun NavController.navigateToEditAluno(id: String? = null) = navigate(route = EditAlunoRoute(id))


@Serializable
data class EditResponsavelRoute(val id: String? = null)

fun NavController.navigateToEditResponsavel(id: String? = null) = navigate(route = EditResponsavelRoute(id))

@Serializable
object SessaoRoute

@Serializable
data class EditSessaoRoute(val id: String? = null)

fun NavController.navigateToSessoes() = navigate(route = SessaoRoute)
fun NavController.navigateToEditSessao(id: String? = null) = navigate(route = EditSessaoRoute(id))