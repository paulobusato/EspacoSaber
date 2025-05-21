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
import br.com.joanadarc.espacosaber.ui.navigation.AlunoRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditAlunoRoute
import br.com.joanadarc.espacosaber.ui.navigation.EditResponsavelRoute
import br.com.joanadarc.espacosaber.ui.navigation.HomeRoute
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
                        HomeRoute()
                    }
                    composable<AlunoRoute> {
                        AlunoRoute()
                    }
                    composable<EditAlunoRoute> {
                        EditAlunoRoute()
                    }
                    composable<EditResponsavelRoute> {
                        EditResponsavelRoute()
                    }
                }
            }
        }
    }
}