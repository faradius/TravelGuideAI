package com.developerscracks.travelguideai.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developerscracks.travelguideai.home.presentation.components.HomeFilterButton
import com.developerscracks.travelguideai.home.presentation.components.HomeFilterDialog
import com.developerscracks.travelguideai.home.presentation.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    if (state.showDialog){
        HomeFilterDialog(
            onDimiss = {
                viewModel.onFilterDismiss()
            }, filterSettings = state.filterSettings, onAction = {
                viewModel.onSenttingsChange(it)
            }
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "A donde viajas?")
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            HomeSearchBar(
                onSearch = {
                    viewModel.search()
                },
                placeholder = "Pais, Ciudad",
                inputText = state.searchText,
                onValueChange = {viewModel.onSearchTextChange(it)}
            )
            HomeFilterButton(onClick = {viewModel.onFilterClick()})
        }
    }
}