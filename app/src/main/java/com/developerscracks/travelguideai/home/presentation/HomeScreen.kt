package com.developerscracks.travelguideai.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.developerscracks.travelguideai.home.presentation.components.HomeFilterButton
import com.developerscracks.travelguideai.home.presentation.components.HomeFilterDialog
import com.developerscracks.travelguideai.home.presentation.components.HomePopularPlaceItem
import com.developerscracks.travelguideai.home.presentation.components.HomePoularFilter
import com.developerscracks.travelguideai.home.presentation.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.showDialog) {
        HomeFilterDialog(onDimiss = {
            viewModel.onFilterDismiss()
        }, filterSettings = state.filterSettings, onAction = {
            viewModel.onSenttingsChange(it)
        })
    }

    BackHandler(state.chatReplay != null) {
        viewModel.onBackPress()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        item {
            Text(
                text = "A donde viajas?",
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeSearchBar(
                    onSearch = {
                        viewModel.search()
                    },
                    placeholder = "Pais, Ciudad",
                    inputText = state.searchText,
                    onValueChange = { viewModel.onSearchTextChange(it) }
                )
                HomeFilterButton(onClick = { viewModel.onFilterClick() })
            }
        }

        if (state.isLoading){
            item {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

        }else {
            state.chatReplay?.let {
                item {
                    Text(text = it, modifier = Modifier.padding(horizontal = 16.dp))
                }
            } ?: item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Lugares Populares",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    HomePoularFilter(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        selectedRegion = state.selectedRegion,
                        selectRegion = {
                            viewModel.onRegionSelect(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(24.dp), // Espacio entre elementos
                        contentPadding = PaddingValues(horizontal = 16.dp) // Inicia con un padding y despues se reduce
                    ) {
                        items(state.popularPlaces) {place ->
                            HomePopularPlaceItem(
                                place = place,
                                onPlaceClick = {
                                    viewModel.onSearchTextChange(it)
                                }
                            )
                        }
                    }
                }
            }
        }


    }
}