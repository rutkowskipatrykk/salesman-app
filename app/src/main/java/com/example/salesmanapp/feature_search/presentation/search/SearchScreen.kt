package com.example.salesmanapp.feature_search.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.salesmanapp.R
import com.example.salesmanapp.feature_search.presentation.search.components.CustomTopBar
import com.example.salesmanapp.feature_search.presentation.search.components.SalesmanRow
import com.example.salesmanapp.feature_search.presentation.search.components.SearchField
import com.example.salesmanapp.ui.theme.spacing

@Composable
fun SearchScreen(
    onBackClick: () -> Unit
) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = stringResource(id = R.string.addresses),
                navigationIcon = {
                    Icon(
                        bitmap = ImageBitmap.imageResource(R.drawable.arrow_back),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.medium)
                            .size(24.dp)
                            .clickable(onClick = onBackClick)
                    )
                }
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
                Spacer(modifier = Modifier.height(24.dp))
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
                    items = viewModel.salesmanList,
                    key = { salesman -> salesman.name to salesman.areas }
                ) { salesman ->
                    SalesmanRow(
                        name = salesman.name,
                        postCodes = salesman.areas
                    )
                }
            }
        }
    }
}