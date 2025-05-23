package br.com.joanadarc.espacosaber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.joanadarc.espacosaber.feature.aluno.AlunoRoute
import br.com.joanadarc.espacosaber.feature.aluno.EditAlunoRoute
import br.com.joanadarc.espacosaber.feature.home.HomeRoute
import br.com.joanadarc.espacosaber.feature.responsavel.EditResponsavelRoute
import br.com.joanadarc.espacosaber.feature.sessao.EditSessaoRoute
import br.com.joanadarc.espacosaber.feature.sessao.SessaoRoute
import br.com.joanadarc.espacosaber.ui.navigation.AlunoRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditAlunoRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditResponsavelRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditSessaoRoute
import br.com.joanadarc.espacosaber.ui.navigation.HomeRoute
import br.com.joanadarc.espacosaber.ui.navigation.SessaoRoute
import br.com.joanadarc.espacosaber.ui.navigation.navigateToAlunos
import br.com.joanadarc.espacosaber.ui.navigation.navigateToEditAluno
import br.com.joanadarc.espacosaber.ui.navigation.navigateToEditSessao
import br.com.joanadarc.espacosaber.ui.navigation.navigateToSessoes
import br.com.joanadarc.espacosaber.ui.theme.EspacoSaberTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            EspacoSaberTheme {
                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                ) {
                    composable<HomeRoute> {
                        HomeRoute(
                            onNavigate = { route ->
                                when (route) {
                                    AlunoRoute -> navController.navigateToAlunos()
                                    SessaoRoute -> navController.navigateToSessoes()
                                }
                            }
                        )
                    }
                    composable<AlunoRoute> {
                        AlunoRoute(
                            onBack = { navController.popBackStack() },
                            onAdd = { navController.navigateToEditAluno() },
                        )
                    }
                    composable<EditAlunoRoute> {
                        EditAlunoRoute(onBack = { navController.popBackStack() })
                    }
                    composable<EditResponsavelRoute> {
                        EditResponsavelRoute()
                    }
                    composable<SessaoRoute> {
                        SessaoRoute(
                            onBack = { navController.popBackStack() },
                            onAdd = { navController.navigateToEditSessao() },
                        )
                    }
                    composable<EditSessaoRoute> {
                        EditSessaoRoute(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}