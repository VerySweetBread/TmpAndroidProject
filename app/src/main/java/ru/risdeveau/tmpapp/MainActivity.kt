package ru.risdeveau.tmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.risdeveau.tmpapp.navigation.PlaylistHost
import ru.risdeveau.tmpapp.ui.theme.TmpappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmpappTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PlaylistHost(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}
