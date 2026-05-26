package ru.risdeveau.tmpapp.ui.screen.alltracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.risdeveau.tmpapp.di.Creator
import ru.risdeveau.tmpapp.domain.interactor.TrackSearchInteractor

class AllTracksViewModel(
    private val trackSearchInteractor: TrackSearchInteractor
) : ViewModel() {
    private val _screenState = MutableStateFlow<AllTracksState>(AllTracksState.Initial)
    val screenState = _screenState.asStateFlow()

    fun fetchData() {
        if (_screenState.value is AllTracksState.Loading) return

        viewModelScope.launch {
            _screenState.update { AllTracksState.Loading }

            try {
                val tracks = withContext(Dispatchers.IO) {
                    trackSearchInteractor.getAllTracks()
                }
                _screenState.update { AllTracksState.Success(tracks) }
            } catch (e: Exception) {
                _screenState.update {
                    AllTracksState.Error(e.message.orEmpty())
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AllTracksViewModel(Creator.getTrackSearchInteractor()) as T
            }
        }
    }
}
