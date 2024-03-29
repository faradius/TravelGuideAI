package com.developerscracks.travelguideai.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeFilterIncrement(
    number: Int,
    onMinus: () -> Unit,
    onPlus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        HomeFilterSettingsButton(onClick = onMinus, icon = Icons.Default.Remove)
        Text(text = "$number")
        HomeFilterSettingsButton(onClick = onPlus, icon = Icons.Default.Add)
    }
}

@Preview
@Composable
fun HomeFilterIncrementPreview() {
    HomeFilterIncrement(number = 5, onMinus = {}, onPlus = {})
}