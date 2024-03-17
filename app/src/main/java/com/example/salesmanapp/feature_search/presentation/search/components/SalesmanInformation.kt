package com.example.salesmanapp.feature_search.presentation.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastJoinToString
import com.example.salesmanapp.ui.theme.spacing

private const val SEPARATOR = ", "

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SalesmanInformation(
    name: String,
    postCodes: List<String>,
    showPostCodes: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
        )
        if (showPostCodes) {
            FlowRow {
                Text(
                    text = postCodes.fastJoinToString(SEPARATOR),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall)
                )

            }
        }
    }
}

@Composable
@Preview
fun SalesmanInformationPreview_TypicalName_TypicalPostCode_Collapsed() {
    SalesmanInformation(
        name = "Anna Muller",
        postCodes = listOf(
            "12345",
            "54321",
        ),
        false
    )
}

@Composable
@Preview
fun SalesmanInformationPreview_TypicalName_TypicalPostCode_Expanded() {
    SalesmanInformation(
        name = "Anna Muller",
        postCodes = listOf(
            "12345",
            "54321",
        ),
        true
    )
}

@Composable
@Preview
fun SalesmanInformationPreview_LongName_LongPostCodesList_Collapsed() {
    SalesmanInformation(
        name = "Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller ",
        postCodes = listOf(
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
        ),
        false
    )
}

@Composable
@Preview
fun SalesmanInformationPreview_LongName_LongPostCodesList_Expanded() {
    SalesmanInformation(
        name = "Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller Anna Muller ",
        postCodes = listOf(
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
            "12345",
            "54321",
        ),
        true
    )
}