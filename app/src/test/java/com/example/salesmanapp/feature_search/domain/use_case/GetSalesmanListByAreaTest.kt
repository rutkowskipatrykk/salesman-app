package com.example.salesmanapp.feature_search.domain.use_case

import app.cash.turbine.test
import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetSalesmanListByAreaTest {

    @MockK
    private lateinit var searchRepository: SearchRepository

    private lateinit var tested: GetSalesmanListByArea

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = GetSalesmanListByArea(searchRepository)
    }

    @Test
    fun `invoke with empty postCode returns empty list`() = runTest {
        // Given
        val postCode = ""

        // When
        val result = tested(postCode)

        // Then
        result.test {
            assertEquals(emptyList<Salesman>(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `invoke with non-empty postCode returns list of salesmen`() = runTest {
        // Given
        val postCode = "12345"
        val salesmanList = listOf(
            Salesman(
                name = "AAA",
                areas = listOf("12345")
            )
        )
        coEvery { searchRepository.searchSalesman(any()) } returns salesmanList

        // When
        val result = tested(postCode)

        // Then
        result.test {
            assertEquals(salesmanList, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}