package ru.risdeveau.tmpapp.domain.interactor

import ru.risdeveau.tmpapp.domain.model.Track

interface TrackSearchInteractor {
    suspend fun searchTracks(expression: String): List<Track>

    suspend fun getAllTracks(): List<Track>
}
