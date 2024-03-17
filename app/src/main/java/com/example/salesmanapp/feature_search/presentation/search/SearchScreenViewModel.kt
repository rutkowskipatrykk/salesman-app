package com.example.salesmanapp.feature_search.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.use_case.GetSalesmanListByArea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel
@Inject
constructor(
    private val getSalesmanListByArea: GetSalesmanListByArea
) : ViewModel() {

    private val _searchValue = mutableStateOf("")
    val searchValue: State<String> = _searchValue

    private val _salesmanList = mutableStateListOf<Salesman>()
    val salesmanList: SnapshotStateList<Salesman> = _salesmanList

    fun dispatchAction(action: SearchScreenAction) {
        when (action) {
            is SearchScreenAction.SearchValueChanged -> {
                _searchValue.value = action.searchValue
                getSalesman(action.searchValue)
            }
        }
    }

    private fun getSalesman(postCode: String) =
        getSalesmanListByArea(postCode)
            .onEach { salesmanList ->
                _salesmanList.clear()
                _salesmanList.addAll(salesmanList)
            }
            .launchIn(viewModelScope)

}