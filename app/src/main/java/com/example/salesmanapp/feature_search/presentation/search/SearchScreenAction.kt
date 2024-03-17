package com.example.salesmanapp.feature_search.presentation.search

sealed class SearchScreenAction {

    data class SearchValueChanged(val searchValue: String) : SearchScreenAction()

}