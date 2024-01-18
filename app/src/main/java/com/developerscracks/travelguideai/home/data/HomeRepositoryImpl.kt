package com.developerscracks.travelguideai.home.data

import com.developerscracks.travelguideai.home.data.remote.ChatgptApi
import com.developerscracks.travelguideai.home.data.remote.dto.ChatRequestDTO
import com.developerscracks.travelguideai.home.data.remote.dto.MessageX
import com.developerscracks.travelguideai.home.domain.HomeRepository

class HomeRepositoryImpl(
    private val api: ChatgptApi
): HomeRepository {
    override suspend fun getTravelGuide(location: String): Result<String> {
        return try {
            val request = ChatRequestDTO(
                maxTokens = 1500,
                model = "gpt-3.5-turbo",
                messages = listOf(
                    MessageX(
                        content = "Sos una guía de viaje. Te voy a dar mi ubicación, y me vas a sugerir lugares para visitar cerca. También te voy a dar los tipo de lugares que quiero visitar. Aparte, quiero que me sugieras lugares de un tipo similar a los que te mencione que estén cerca de mi primera ubicación. Estoy en ${location} y quiero visitar: Museos, Restaurantes. Dame los precios de cada lugar en USD. Ordenarlos por tipo de lugar.",
                        role = "assistant"
                    )
                ),
                temperature = 0.7
            )
            val information = api.getTravelInformation(request)
            Result.success(information.choices.first().message.content)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}