package com.example.salesmanapp.feature_search.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.salesmanapp.feature_search.presentation.search.components.SearchField

@Composable
fun SearchScreen() {
    val viewmodel = hiltViewModel<SearchScreenViewModel>()
    var searchValue by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        SearchField(
            value = searchValue,
            onValueChanged = {
                Log.d("SearchScreen", "onValueChanged: $it")
                searchValue = it
            }
        )
        Text("Search screen!")
    }
}