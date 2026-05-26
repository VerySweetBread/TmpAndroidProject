package ru.risdeveau.tmpapp.domain.repository

import ru.risdeveau.tmpapp.domain.model.Track

interface TracksRepository {
    suspend fun searchTracks(expression: String): List<Track>

    suspend fun getAllTracks(): List<Track>
}
