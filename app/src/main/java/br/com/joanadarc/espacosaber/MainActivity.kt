package br.com.joanadarc.espacosaber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.joanadarc.espacosaber.ui.screens.HomeRoute
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
                }
            }
        }
    }
}