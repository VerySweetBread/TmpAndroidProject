package ru.risdeveau.tmpapp.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.rabehx.iconsax.Iconsax
import io.github.rabehx.iconsax.automirrored.outline.ArrowLeft
import io.github.rabehx.iconsax.automirrored.outline.ArrowRight2
import io.github.rabehx.iconsax.outline.MessageQuestion
import io.github.rabehx.iconsax.outline.Share
import ru.risdeveau.tmpapp.R
import splitties.init.appCtx
import splitties.resources.str

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick) {
                    Icon(Iconsax.AutoMirrored.Outline.ArrowLeft, "назад")
                }
            },
            title = {
                Text(appCtx.str(R.string.settings))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
        )

        MenuRow(appCtx.str(R.string.dark_theme)) { Switch(isSystemInDarkTheme(), {}, enabled = false) }
        MenuRow(appCtx.str(R.string.share_app)) { Icon(Iconsax.Outline.Share, appCtx.str(R.string.share_app)) }
        MenuRow(appCtx.str(R.string.support)) { Icon(Iconsax.Outline.MessageQuestion, appCtx.str(R.string.support)) }
        MenuRow(appCtx.str(R.string.license)) { Icon(Iconsax.AutoMirrored.Outline.ArrowRight2, appCtx.str(R.string.license)) }
    }
}

@Composable
private fun MenuRow(
    title: String,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(12.dp))

        content()
    }
}