package com.example.salesmanapp.feature_search.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.salesmanapp.R
import com.example.salesmanapp.feature_search.presentation.search.components.SalesmanRow
import com.example.salesmanapp.feature_search.presentation.search.components.SearchField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.addresses),
                        style = MaterialTheme.typography.titleSmall,
                    )
                },
                navigationIcon = {
                    Icon(
                        bitmap = ImageBitmap.imageResource(R.drawable.arrow_back),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
            )

        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            item {
                SearchField(
                    value = viewModel.searchValue.value,
                    maxQueryLength = 5,
                    onValueChanged = { newSearchQuery ->
                        viewModel.dispatchAction(
                            SearchScreenAction.SearchValueChanged(
                                newSearchQuery
                            )
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(36.dp))
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
            } else {
                items(
                    count = viewModel.salesmanList.size,
                    key = { viewModel.salesmanList[it].name },
                ) {
                    SalesmanRow(
                        name = viewModel.salesmanList[it].name,
                        postCodes = viewModel.salesmanList[it].areas
                    )
                }
            }
        }
    }
}