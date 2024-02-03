package com.developerscracks.travelguideai.home.presentation

import com.developerscracks.travelguideai.home.domain.model.HomeFilterSettings
import com.developerscracks.travelguideai.home.domain.model.Place
import com.developerscracks.travelguideai.home.domain.model.Region

data class HomeState(
    val searchText: String = "",
    val showDialog: Boolean = false,
    val filterSettings: HomeFilterSettings = HomeFilterSettings(),
    val filterSettingsBackup: HomeFilterSettings = filterSettings,
    val chatReplay: String? = null,
    val selectedRegion: Region = Region.TODAS,
    val popularPlaces: List<Place> = emptyList(),
    val popularPlacesBackup: List<Place> = popularPlaces
)
