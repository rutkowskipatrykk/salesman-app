package com.example.salesmanapp.feature_search.data.repository

import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import javax.inject.Inject

private const val ASTRIX = "*"

class SearchRepositoryImpl
@Inject
constructor() : SearchRepository {

    private val salesmanList = listOf(
        Salesman(name = "Artem Titarenko", areas = listOf("76133")),
        Salesman(name = "Bernd Schmitt", areas = listOf("7619*")),
        Salesman(name = "Chris Krapp", areas = listOf("762*")),
        Salesman(name = "Alex Uber", areas = listOf("86*"))
    )

    override suspend fun searchSalesman(postCodeQuery: String): List<Salesman> {
        return if (postCodeQuery.isEmpty()) {
            emptyList()
        } else {
            salesmanList.filter {
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