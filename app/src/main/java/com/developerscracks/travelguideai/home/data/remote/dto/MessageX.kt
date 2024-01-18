package com.developerscracks.travelguideai.home.data.remote.dto


import com.squareup.moshi.Json

data class MessageX(
    @Json(name = "content")
    val content: String,
    @Json(name = "role")
    val role: String
)