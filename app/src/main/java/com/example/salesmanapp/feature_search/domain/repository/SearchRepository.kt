package com.example.salesmanapp.feature_search.domain.repository

import com.example.salesmanapp.feature_search.domain.model.Salesman

interface SearchRepository {
    suspend fun searchSalesman(postCode: String): List<Salesman>

}