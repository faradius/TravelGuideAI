package com.developerscracks.travelguideai.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.developerscracks.travelguideai.home.domain.model.Region
import com.developerscracks.travelguideai.ui.theme.DarkGreen


@Composable
fun HomePoularFilter(
    selectedRegion: Region,
    modifier: Modifier,
    selectRegion: (Region) -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Region.values().forEach{
            val textColor = if (it == selectedRegion) DarkGreen else Color.Gray
            TextButton(onClick = { selectRegion(it) }, colors = ButtonDefaults.textButtonColors(
                contentColor = DarkGreen

            )) {
                Text(text = it.name.lowercase().capitalize(Locale.current), color = textColor)
            }
        }
    }
}