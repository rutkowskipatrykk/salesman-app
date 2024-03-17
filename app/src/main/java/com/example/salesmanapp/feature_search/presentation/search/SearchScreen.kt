package com.example.salesmanapp.feature_search.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.salesmanapp.feature_search.presentation.search.components.SalesmanRow
import com.example.salesmanapp.feature_search.presentation.search.components.SearchField

@Composable
fun SearchScreen() {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            SearchField(
                value = viewModel.searchValue.value,
                maxQueryLength = 5,
                onValueChanged = {
                    viewModel.dispatchAction(SearchScreenAction.SearchValueChanged(it))
                }
            )
        }

        if (viewModel.isLoading.value) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        else {
            items(
                count = viewModel.salesmanList.size,
                key = { viewModel.salesmanList[it].name },
            ) {
                SalesmanRow(
                    name = viewModel.salesmanList[it].name,
                    postCodes = viewModel.salesmanList[it].areas,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}