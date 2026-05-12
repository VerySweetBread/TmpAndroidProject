package ru.risdeveau.tmpapp.ui.screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import io.github.rabehx.iconsax.Iconsax
import io.github.rabehx.iconsax.automirrored.outline.ArrowLeft
import io.github.rabehx.iconsax.automirrored.outline.ArrowRight2
import io.github.rabehx.iconsax.outline.MessageQuestion
import io.github.rabehx.iconsax.outline.Share
import ru.risdeveau.tmpapp.R
import splitties.resources.str
import splitties.toast.UnreliableToastApi
import splitties.toast.toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick) {
                    Icon(Iconsax.AutoMirrored.Outline.ArrowLeft, "назад")
                }
            },
            title = {
                Text(context.str(R.string.settings))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
        )

        MenuRow(
            title = context.str(R.string.dark_theme),
            content = { Switch(isSystemInDarkTheme(), {}, enabled = false) }
        )
        MenuRow(
            title = context.str(R.string.share_app),
            content = { Icon(Iconsax.Outline.Share, context.str(R.string.share_app)) },
            onClick = { shareApp(context) },
        )
        MenuRow(
            title = context.str(R.string.support),
            content = { Icon(Iconsax.Outline.MessageQuestion, context.str(R.string.support)) },
            onClick = { openEmailClient(context) }
        )
        MenuRow(
            title = context.str(R.string.license),
            content = { Icon(Iconsax.AutoMirrored.Outline.ArrowRight2, context.str(R.string.license)) },
            onClick = { openBrowser(context) }
        )
    }
}

@OptIn(UnreliableToastApi::class)
@Composable
private fun MenuRow(
    title: String,
    content: @Composable () -> Unit,
    onClick: () -> Unit = { toast("Not implemented")}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(12.dp))

        content()
    }
}

@OptIn(UnreliableToastApi::class)
private fun shareApp(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, context.str(R.string.share_app_text))
    }

    try {
        val chooser = Intent.createChooser(intent, null)
        context.startActivity(chooser)
    } catch (_: ActivityNotFoundException) {
        toast(context.str(R.string.no_apps_found))
    }
}

@OptIn(UnreliableToastApi::class)
private fun openEmailClient(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:".toUri()
        putExtra(Intent.EXTRA_EMAIL, arrayOf(context.str(R.string.developer_email)))
        putExtra(Intent.EXTRA_SUBJECT, context.str(R.string.developer_email_subject))
        putExtra(Intent.EXTRA_TEXT, context.str(R.string.developer_email_body))
    }

    try {
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        toast(context.str(R.string.no_apps_found))
    }
}

@OptIn(UnreliableToastApi::class)
private fun openBrowser(context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        context.str(R.string.practicum_offer_url).toUri()
    )

    try {
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        toast(context.str(R.string.no_apps_found))
    }
}