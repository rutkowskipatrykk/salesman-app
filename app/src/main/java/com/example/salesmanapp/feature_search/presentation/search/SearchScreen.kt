package com.example.salesmanapp.feature_search.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen() {
    val viewmodel = hiltViewModel<SearchScreenViewModel>()
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Search screen!")
    }
}