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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private val SEARCH_DELAY = 1000L

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchScreenViewModel
@Inject
constructor(
    private val getSalesmanListByArea: GetSalesmanListByArea
) : ViewModel() {

    private val searchFlow: MutableSharedFlow<String> = MutableSharedFlow()

    private val _searchValue = mutableStateOf("")
    val searchValue: State<String> = _searchValue

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _salesmanList = mutableStateListOf<Salesman>()
    val salesmanList: SnapshotStateList<Salesman> = _salesmanList

    init {
        searchFlow
            .debounce(SEARCH_DELAY)
            .onEach { postCode ->
                getSalesman(postCode)
            }
            .launchIn(viewModelScope)
    }

    fun dispatchAction(action: SearchScreenAction) {
        when (action) {
            is SearchScreenAction.SearchValueChanged -> {
                _isLoading.value = true
                _searchValue.value = action.searchValue
                viewModelScope.launch {
                    searchFlow
                        .emit(action.searchValue)
                }
            }
        }
    }

    private fun getSalesman(postCode: String) =
        getSalesmanListByArea(postCode)
            .onEach { salesmanList ->
                _salesmanList.clear()
                _salesmanList.addAll(salesmanList)
            }
            .onCompletion { _isLoading.value = false }
            .launchIn(viewModelScope)

}