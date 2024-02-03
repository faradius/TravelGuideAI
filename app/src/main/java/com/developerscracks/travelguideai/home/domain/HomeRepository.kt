package com.developerscracks.travelguideai.home.domain

import com.developerscracks.travelguideai.home.domain.model.Place

interface HomeRepository {
    suspend fun getTravelGuide(location: String):Result<String>

    suspend fun getPopularPlaces():Result<List<Place>>
}