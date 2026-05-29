package ru.risdeveau.tmpapp.ui.screen.search

import ru.risdeveau.tmpapp.domain.model.Track

sealed interface SearchState {
    data object Initial : SearchState
    data object Searching : SearchState
    data class Success(val tracks: List<Track>) : SearchState
    data class Fail(val message: String) : SearchState
}
