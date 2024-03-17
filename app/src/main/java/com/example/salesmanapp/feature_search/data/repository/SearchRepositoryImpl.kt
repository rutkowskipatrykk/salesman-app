package com.example.salesmanapp.feature_search.data.repository

import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl
@Inject
constructor() : SearchRepository {
    override suspend fun searchSalesman(postCode: String): List<Salesman> {
        return listOf(
            Salesman(name = "Artem Titarenko", areas = listOf("76133")),
            Salesman(name = "Bernd Schmitt", areas = listOf("7619*")),
            Salesman(name = "Chris Krapp", areas = listOf("762*")),
            Salesman(name = "Alex Uber", areas = listOf("86*"))
        )
    }
}