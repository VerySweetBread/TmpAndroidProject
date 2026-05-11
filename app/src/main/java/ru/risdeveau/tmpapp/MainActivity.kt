package ru.risdeveau.tmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.risdeveau.tmpapp.ui.screen.MainScreen
import ru.risdeveau.tmpapp.ui.screen.SearchScreen
import ru.risdeveau.tmpapp.ui.screen.Settings
import ru.risdeveau.tmpapp.ui.theme.TmpappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmpappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            MainScreen(
                                Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background),
                                navController
                            )
                        }

                        composable("search") {
                            SearchScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable("settings") {
                            Settings(Modifier.fillMaxSize()) { navController.popBackStack() }
                        }
                    }
                }
            }
        }
    }
}