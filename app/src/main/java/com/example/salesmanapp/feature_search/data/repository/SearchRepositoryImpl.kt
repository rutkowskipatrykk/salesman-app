package com.example.salesmanapp.feature_search.data.repository

import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import javax.inject.Inject

private const val ASTRIX = "*"

class SearchRepositoryImpl
@Inject
constructor(
    private val mockSalesmanListResponse: List<Salesman>
) : SearchRepository {

    override suspend fun searchSalesman(postCodeQuery: String): List<Salesman> {
        return if (postCodeQuery.isEmpty()) {
            emptyList()
        } else {
            mockSalesmanListResponse.filter {
                it.areas.any { area ->
                    if (area.contains(ASTRIX) && area.length <= postCodeQuery.length) {
                        val querySubstring = postCodeQuery.substring(0, area.indexOf(ASTRIX))
                        querySubstring == area.substring(0, area.indexOf(ASTRIX))
                    } else {
                        area.startsWith(postCodeQuery)
                    }
                }
            }
        }
    }
}