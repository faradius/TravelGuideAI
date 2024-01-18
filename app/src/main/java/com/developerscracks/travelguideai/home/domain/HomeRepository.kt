package com.developerscracks.travelguideai.home.domain

interface HomeRepository {
    suspend fun getTravelGuide(location: String):Result<String>
}