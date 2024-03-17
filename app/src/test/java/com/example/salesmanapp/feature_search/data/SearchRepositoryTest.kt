package com.example.salesmanapp.feature_search.data

import com.example.salesmanapp.feature_search.data.repository.SearchRepositoryImpl
import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import io.mockk.MockKAnnotations
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest {

    private lateinit var tested: SearchRepository

    private val mockSalesmanListResponse: List<Salesman> = listOf(
        Salesman(name = "Artem Titarenko", areas = listOf("76133")),
        Salesman(name = "Bernd Schmitt", areas = listOf("7619*")),
        Salesman(name = "Chris Krapp", areas = listOf("762*")),
        Salesman(name = "Alex Uber", areas = listOf("86*"))
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = SearchRepositoryImpl(mockSalesmanListResponse)
    }

    @Test
    fun `invoke with empty postCode returns empty list`() = runTest {
        // Given
        val postCode = ""

        // When
        val result = tested.searchSalesman(postCode)

        // Then
        assert(result.isEmpty())
    }

    @Test
    fun `invoke with postCode equals to 7 returns list of 3 salesman`() = runTest {
        // Given
        val postCode = "7"

        // When
        val result = tested.searchSalesman(postCode)

        // Then
        assertEquals(3, result.size)
        assertEquals(mockSalesmanListResponse[0], result[0])
        assertEquals(mockSalesmanListResponse[1], result[1])
        assertEquals(mockSalesmanListResponse[2], result[2])
    }

    @Test
    fun `invoke with postCode equals to 86111 returns list of one salesmen with astrix`() = runTest {
        // Given
        val postCode = "86111"

        // When
        val result = tested.searchSalesman(postCode)

        // Then
        assertEquals(1, result.size)
        assertEquals(mockSalesmanListResponse[3], result.first())
    }
}