package com.developerscracks.travelguideai.home.data

import com.developerscracks.travelguideai.home.data.remote.ChatgptApi
import com.developerscracks.travelguideai.home.data.remote.dto.ChatRequestDTO
import com.developerscracks.travelguideai.home.data.remote.dto.MessageX
import com.developerscracks.travelguideai.home.domain.HomeRepository
import com.developerscracks.travelguideai.home.domain.model.Place
import com.developerscracks.travelguideai.home.domain.model.Region

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

    override suspend fun getPopularPlaces(): Result<List<Place>> {
        return Result.success(
            listOf(
                Place("USA", "New York", Region.AMERICA, "https://www.civitatis.com/f/estados-unidos/nueva-york/galeria/big/paseo-barco-rio-hudson.jpg"),
                Place("Argentina", "Salta", Region.AMERICA, "https://www.wallpaperup.com/uploads/wallpapers/2020/05/21/1376837/b4e47190594a0429ad529f6f60504c11-1000.jpg"),
                Place("España", "Barcelona", Region.EUROPA, "https://www.fodors.com/wp-content/uploads/2022/03/Hero-UPDATEBarcelona-iStock-1320014700-1.jpg"),
                Place("Australia", "Sydney", Region.OCEANIA, "https://images.squarespace-cdn.com/content/v1/55ee34aae4b0bf70212ada4c/1577545161018-1F9Z9ZZQG9JO2O4WCWQX/keith-zhu-qaNcz43MeY8-unsplash+%281%29.jpg"),
                Place("Japon", "Tokio", Region.ASIA, "https://lonelyplanetes.cdnstatics2.com/sites/default/files/styles/max_1300x1300/public/fotos/japon_tokio_shibuya_shutterstock_666197236_f11photo_shutterstock.jpg"),
                Place("Italia", "Roma", Region.EUROPA, "https://www.10wallpaper.com/wallpaper/1366x768/1408/Amalfi_Coast_Italy-City_HD_Wallpaper_1366x768.jpg")
            )
        )
    }
}