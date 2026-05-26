package ru.risdeveau.tmpapp.data.network

import ru.risdeveau.tmpapp.data.Storage
import ru.risdeveau.tmpapp.data.dto.BaseResponse
import ru.risdeveau.tmpapp.data.dto.TracksSearchRequest
import ru.risdeveau.tmpapp.data.dto.TracksSearchResponse

class RetrofitNetworkClient(
    private val storage: Storage
) : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        if (dto !is TracksSearchRequest) {
            return BaseResponse().apply { resultCode = 400 }
        }

        val searchList = storage.search(dto.expression)
        return TracksSearchResponse(searchList).apply { resultCode = 200 }
    }
}
