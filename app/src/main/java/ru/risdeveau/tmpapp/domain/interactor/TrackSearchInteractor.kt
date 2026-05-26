package ru.risdeveau.tmpapp.domain.interactor

import ru.risdeveau.tmpapp.domain.model.Track

interface TrackSearchInteractor {
    suspend fun getAllTracks(): List<Track>
}
