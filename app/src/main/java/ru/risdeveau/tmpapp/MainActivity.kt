package ru.risdeveau.tmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rabehx.iconsax.Iconsax
import io.github.rabehx.iconsax.automirrored.filled.ArrowRight2
import io.github.rabehx.iconsax.automirrored.filled.ArrowRight3
import io.github.rabehx.iconsax.automirrored.outline.ArrowRight2
import io.github.rabehx.iconsax.outline.Heart
import io.github.rabehx.iconsax.outline.MusicPlaylist
import io.github.rabehx.iconsax.outline.SearchNormal
import io.github.rabehx.iconsax.outline.Setting
import io.github.rabehx.iconsax.outline.Setting2
import io.github.rabehx.iconsax.outline.Settings
import ru.risdeveau.tmpapp.ui.theme.TmpappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TmpappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Header()

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 72.dp)
                                .clip(RoundedCornerShape(
                                    topStart = 16.dp,
                                    topEnd = 16.dp
                                ))
                                .background(MaterialTheme.colorScheme.background)
                                .padding(top = 16.dp)
                        ) {
                            MenuRow(Iconsax.Outline.SearchNormal, "Поиск")
                            MenuRow(Iconsax.Outline.MusicPlaylist, "Плейлисты")
                            MenuRow(Iconsax.Outline.Heart, "Избранное")
                            MenuRow(Iconsax.Outline.Setting2, "Настройки")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Playlist maker",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight
        )
    }
}

@Composable
private fun MenuRow(
    icon: ImageVector,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Iconsax.AutoMirrored.Outline.ArrowRight2,
            contentDescription = null
        )
    }
}