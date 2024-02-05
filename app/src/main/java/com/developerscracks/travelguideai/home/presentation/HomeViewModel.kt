package com.developerscracks.travelguideai.home.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerscracks.travelguideai.home.domain.HomeRepository
import com.developerscracks.travelguideai.home.domain.model.Region
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
    private set

    init {
        viewModelScope.launch {
            repository.getPopularPlaces().onSuccess {
                state = state.copy(
                    popularPlaces = it,
                    popularPlacesBackup = it
                )
            }.onFailure {
                println("Hubo un error init")
            }
        }
    }
    init {
        /*viewModelScope.launch {
            repository.getTravelGuide().onSuccess {
                Log.d("TAG", it)
            }.onFailure {
                println("Hubo un error")
            }
        }*/
    }

    fun onSearchTextChange(newText: String){
        state = state.copy(searchText = newText)
    }

    fun search(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getTravelGuide(state.searchText).onSuccess {
                state = state.copy(chatReplay = it)
            }.onFailure {
                println("Hubo un error")
            }

            state = state.copy(isLoading = false)
        }
    }

    fun onRegionSelect(region: Region){
        state = state.copy(
            selectedRegion = region,
            popularPlaces = if (region != Region.TODAS) state.popularPlacesBackup.filter { it.region == region } else state.popularPlacesBackup

        )
    }

    fun onSenttingsChange(actions: HomeFilterDialogAction){
        when(actions){
            HomeFilterDialogAction.OnApplyClick ->{
                state = state.copy(
                    filterSettingsBackup = state.filterSettings,
                    showDialog = false
                )
            }
            HomeFilterDialogAction.OnMuseumClick ->{
                state = state.copy(
                    filterSettings = state.filterSettings.copy(
                        museums = !state.filterSettings.museums
                    )
                )
            }
            HomeFilterDialogAction.OnPeopleMinus ->{
                state = state.copy(filterSettings = state.filterSettings.copy(people = state.filterSettings.people - 1))
            }
            HomeFilterDialogAction.OnPeoplePlus ->{
                state = state.copy(filterSettings = state.filterSettings.copy(people = state.filterSettings.people + 1))
            }
            HomeFilterDialogAction.OnRestaurantClick ->{
                state = state.copy(
                    filterSettings = state.filterSettings.copy(
                        restaurant = !state.filterSettings.restaurant
                    )
                )
            }
        }
    }

    fun onFilterClick(){
        state = state.copy(showDialog = true)
    }

    fun onFilterDismiss(){
        state = state.copy(
            showDialog = false,
            filterSettings = state.filterSettingsBackup
        )
    }

    fun onBackPress(){
        state = state.copy(
            chatReplay = null
        )
    }
}