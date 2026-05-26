package ru.risdeveau.tmpapp.di

import ru.risdeveau.tmpapp.data.Storage
import ru.risdeveau.tmpapp.data.TracksRepositoryImpl
import ru.risdeveau.tmpapp.data.network.NetworkClient
import ru.risdeveau.tmpapp.data.network.RetrofitNetworkClient
import ru.risdeveau.tmpapp.domain.interactor.TrackSearchInteractor
import ru.risdeveau.tmpapp.domain.interactor.TrackSearchInteractorImpl
import ru.risdeveau.tmpapp.domain.repository.TracksRepository

object Creator {
    private val storage: Storage by lazy {
        Storage()
    }

    private val networkClient: NetworkClient by lazy {
        RetrofitNetworkClient(storage)
    }

    private val repository: TracksRepository by lazy {
        TracksRepositoryImpl(networkClient)
    }

    private val searchInteractor: TrackSearchInteractor by lazy {
        TrackSearchInteractorImpl(repository)
    }

    fun getTracksRepository(): TracksRepository {
        return repository
    }

    fun getTrackSearchInteractor(): TrackSearchInteractor {
        return searchInteractor
    }
}
