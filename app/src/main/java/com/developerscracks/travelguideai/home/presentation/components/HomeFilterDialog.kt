package com.developerscracks.travelguideai.home.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerscracks.travelguideai.home.presentation.HomeFilterDialogAction
import com.developerscracks.travelguideai.home.domain.model.HomeFilterSettings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFilterDialog(
    onDimiss: () -> Unit,
    filterSettings: HomeFilterSettings,
    onAction: (HomeFilterDialogAction) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = onDimiss, modifier = modifier.fillMaxWidth(), buttons = {
        Button(onClick = { onAction(HomeFilterDialogAction.OnApplyClick) }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = "Aplicar")
        }
    }, text = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Personas")
                HomeFilterIncrement(
                    number = filterSettings.people,
                    onMinus = { onAction(HomeFilterDialogAction.OnPeopleMinus) },
                    onPlus = { onAction(HomeFilterDialogAction.OnPeoplePlus) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Restaurantes")
                HomeFilterCheckbox(
                    onClick = { onAction(HomeFilterDialogAction.OnRestaurantClick) },
                    isChecked = filterSettings.restaurant
                )            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Museos")
                HomeFilterCheckbox(
                    onClick = { onAction(HomeFilterDialogAction.OnMuseumClick) },
                    isChecked = filterSettings.museums
                )            }
        }

    }, shape = RoundedCornerShape(30.dp))
}

@Preview
@Composable
fun HomeFilterDialogPreview() {
    //HomeFilterDialog()
}