package com.example.salesmanapp.feature_search.domain.repository

import com.example.salesmanapp.feature_search.domain.model.Salesman

interface SearchRepository {

    suspend fun searchSalesman(postCodeQuery: String): List<Salesman>

}