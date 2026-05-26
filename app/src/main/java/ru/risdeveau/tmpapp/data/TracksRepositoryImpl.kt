package ru.risdeveau.tmpapp.data

import kotlinx.coroutines.delay
import ru.risdeveau.tmpapp.data.dto.TracksSearchRequest
import ru.risdeveau.tmpapp.data.dto.TracksSearchResponse
import ru.risdeveau.tmpapp.data.network.NetworkClient
import ru.risdeveau.tmpapp.domain.model.Track
import ru.risdeveau.tmpapp.domain.repository.TracksRepository

class TracksRepositoryImpl(
    private val networkClient: NetworkClient
) : TracksRepository {
    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        delay(1000)

        return if (response.resultCode == 200) {
            (response as TracksSearchResponse).results.map { trackDto ->
                Track(
                    trackName = trackDto.trackName,
                    artistName = trackDto.artistName,
                    trackTime = formatTrackTime(trackDto.trackTimeMillis)
                )
            }
        } else {
            emptyList()
        }
    }

    override suspend fun getAllTracks(): List<Track> {
        return searchTracks("")
    }

    private fun formatTrackTime(trackTimeMillis: Int): String {
        val seconds = trackTimeMillis / 1000
        val minutes = seconds / 60
        return "%02d:%02d".format(minutes, seconds - minutes * 60)
    }
}
