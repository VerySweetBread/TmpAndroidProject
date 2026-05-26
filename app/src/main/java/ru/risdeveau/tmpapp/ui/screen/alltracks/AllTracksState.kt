package ru.risdeveau.tmpapp.ui.screen.alltracks

import ru.risdeveau.tmpapp.domain.model.Track

sealed interface AllTracksState {
    data object Initial : AllTracksState
    data object Loading : AllTracksState
    data class Success(val tracks: List<Track>) : AllTracksState
    data class Error(val message: String) : AllTracksState
}
