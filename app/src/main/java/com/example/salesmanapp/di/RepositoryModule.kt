package com.example.salesmanapp.di

import com.example.salesmanapp.feature_search.data.repository.SearchRepositoryImpl
import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideSearchRepository(): SearchRepository = SearchRepositoryImpl(
        listOf(
            Salesman(name = "Artem Titarenko", areas = listOf("76133")),
            Salesman(name = "Bernd Schmitt", areas = listOf("7619*")),
            Salesman(name = "Chris Krapp", areas = listOf("762*")),
            Salesman(name = "Alex Uber", areas = listOf("86*"))
        )
    )

}