package com.example.salesmanapp.feature_search.domain.use_case

import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSalesmanListByArea
@Inject
constructor(
    private val searchRepository: SearchRepository
){

    operator fun invoke(postCode: String) = flow {
        emit(searchRepository.searchSalesman(postCode))
    }

}