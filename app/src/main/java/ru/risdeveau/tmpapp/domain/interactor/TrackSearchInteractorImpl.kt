package ru.risdeveau.tmpapp.domain.interactor

import ru.risdeveau.tmpapp.domain.model.Track
import ru.risdeveau.tmpapp.domain.repository.TracksRepository

class TrackSearchInteractorImpl(
    private val repository: TracksRepository
) : TrackSearchInteractor {
    override suspend fun getAllTracks(): List<Track> {
        return repository.getAllTracks()
    }
}
