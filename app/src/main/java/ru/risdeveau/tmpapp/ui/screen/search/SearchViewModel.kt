package ru.risdeveau.tmpapp.ui.screen.search

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
import ru.risdeveau.tmpapp.domain.repository.TracksRepository

class SearchViewModel(
    private val tracksRepository: TracksRepository
) : ViewModel() {
    private val _screenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val screenState = _screenState.asStateFlow()

    fun search(expression: String) {
        val query = expression.trim()
        if (query.isEmpty()) {
            _screenState.update { SearchState.Initial }
            return
        }

        viewModelScope.launch {
            _screenState.update { SearchState.Searching }

            try {
                val tracks = withContext(Dispatchers.IO) {
                    tracksRepository.searchTracks(query)
                }
                _screenState.update { SearchState.Success(tracks) }
            } catch (e: Exception) {
                _screenState.update {
                    SearchState.Fail(e.message.orEmpty())
                }
            }
        }
    }

    fun clearSearch() {
        _screenState.update { SearchState.Initial }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SearchViewModel(Creator.getTracksRepository()) as T
            }
        }
    }
}
