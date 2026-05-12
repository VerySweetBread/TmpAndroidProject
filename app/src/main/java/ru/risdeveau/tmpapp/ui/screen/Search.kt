package ru.risdeveau.tmpapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.github.rabehx.iconsax.Iconsax
import io.github.rabehx.iconsax.automirrored.outline.ArrowLeft
import io.github.rabehx.iconsax.outline.Eraser1
import io.github.rabehx.iconsax.outline.SearchNormal
import ru.risdeveau.tmpapp.R
import splitties.init.appCtx
import splitties.resources.str

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val searchState = rememberTextFieldState()
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Iconsax.AutoMirrored.Outline.ArrowLeft,
                        contentDescription = "назад"
                    )
                }
            },
            title = {
                Text(stringResource(R.string.search))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                navigationIconContentColor = MaterialTheme.colorScheme.onBackground
            ),
            windowInsets = WindowInsets(0, 0, 0, 0)
        )

        SearchField(
            searchState,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .focusRequester(focusRequester)
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
private fun SearchField(
    state: TextFieldState,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        state = state,
        label = { Text(appCtx.str(R.string.search)) },
        placeholder = { Text(appCtx.str(R.string.search)) },
        leadingIcon = { Icon(Iconsax.Outline.SearchNormal, null) },
        trailingIcon = {
            if (state.text.isNotEmpty())
                Icon(
                    Iconsax.Outline.Eraser1,
                    appCtx.str(R.string.erase),
                    Modifier.clickable(onClick = { state.clearText() })
                )
       },
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions( imeAction = ImeAction.Search ),
    )
}