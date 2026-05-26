package ru.risdeveau.tmpapp.data.network

import ru.risdeveau.tmpapp.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}
