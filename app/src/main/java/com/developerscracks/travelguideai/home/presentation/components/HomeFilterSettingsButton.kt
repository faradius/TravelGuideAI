package com.developerscracks.travelguideai.home.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerscracks.travelguideai.ui.theme.DarkGreen

@Composable
fun HomeFilterSettingsButton(
    onClick: () -> Unit,
    icon: ImageVector?,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(ButtonDefaults.MinHeight, ButtonDefaults.MinHeight),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        if (icon != null){
            Icon(imageVector = icon, contentDescription = "icon", tint = DarkGreen)
        }
    }
}

@Preview
@Composable
fun HomeFilterSettingsButtonPreview() {
    HomeFilterSettingsButton(onClick = {}, icon = null)
}