package ru.risdeveau.tmpapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.risdeveau.tmpapp.ui.screen.MainScreen
import ru.risdeveau.tmpapp.ui.screen.SearchScreen
import ru.risdeveau.tmpapp.ui.screen.SettingsScreen
import ru.risdeveau.tmpapp.ui.screen.alltracks.AllTracksScreen

enum class PlaylistScreen(val route: String) {
    Main("main"),
    Search("search"),
    AllTracks("all_tracks"),
    Settings("settings")
}

@Composable
fun PlaylistHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    fun navigateTo(screen: PlaylistScreen) {
        navController.navigate(screen.route) {
            launchSingleTop = true
        }
    }

    fun navigateToSearch() {
        navigateTo(PlaylistScreen.Search)
    }

    fun navigateToAllTracks() {
        navigateTo(PlaylistScreen.AllTracks)
    }

    fun navigateToSettings() {
        navigateTo(PlaylistScreen.Settings)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.Main.route,
        modifier = modifier
    ) {
        composable(PlaylistScreen.Main.route) {
            MainScreen(
                onSearchClick = ::navigateToSearch,
                onAllTracksClick = ::navigateToAllTracks,
                onSettingsClick = ::navigateToSettings
            )
        }

        composable(PlaylistScreen.Search.route) {
            SearchScreen(
                onBackClick = ::navigateBack
            )
        }

        composable(PlaylistScreen.AllTracks.route) {
            AllTracksScreen(
                onBackClick = ::navigateBack
            )
        }

        composable(PlaylistScreen.Settings.route) {
            SettingsScreen(
                onBackClick = ::navigateBack
            )
        }
    }
}