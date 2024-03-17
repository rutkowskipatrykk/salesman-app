package com.example.salesmanapp.feature_search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesmanapp.feature_search.domain.use_case.GetSalesmanListByArea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel
@Inject
constructor(
    private val getSalesmanListByArea: GetSalesmanListByArea
) : ViewModel() {

    fun getSalesman(postCode: String) =
        getSalesmanListByArea(postCode).launchIn(viewModelScope)

}