package com.developerscracks.travelguideai.home.data.remote

import com.developerscracks.travelguideai.home.data.remote.dto.ChatRequestDTO
import com.developerscracks.travelguideai.home.data.remote.dto.ChatResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatgptApi {
    companion object{
        const val BASE_URL = "https://api.openai.com/v1/"
    }

    @POST("chat/completions")
    suspend fun getTravelInformation(@Body body: ChatRequestDTO): ChatResponseDTO
}