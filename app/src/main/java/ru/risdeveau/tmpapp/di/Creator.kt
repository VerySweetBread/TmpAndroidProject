package ru.risdeveau.tmpapp.di

import ru.risdeveau.tmpapp.data.TracksRepositoryImpl
import ru.risdeveau.tmpapp.domain.interactor.TrackSearchInteractor
import ru.risdeveau.tmpapp.domain.interactor.TrackSearchInteractorImpl
import ru.risdeveau.tmpapp.domain.repository.TracksRepository

object Creator {
    private val tracksRepository: TracksRepository by lazy {
        TracksRepositoryImpl()
    }

    private val searchInteractor: TrackSearchInteractor by lazy {
        TrackSearchInteractorImpl(tracksRepository)
    }

    fun getTrackSearchInteractor(): TrackSearchInteractor {
        return searchInteractor
    }
}
