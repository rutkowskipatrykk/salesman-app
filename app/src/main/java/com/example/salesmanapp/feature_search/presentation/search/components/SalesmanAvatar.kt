package com.example.salesmanapp.feature_search.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
fun SalesmanAvatar(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
            .background(Color.LightGray)
            .size(
                width = 41.dp,
                height = 42.dp
            )
    ) {
        Text(text = name.trim().first().toString())
    }
}

@Preview
@PreviewLightDark
@PreviewDynamicColors
@Composable
fun SalesmanAvatarPreview() {
    SalesmanAvatar("Patryk")
}