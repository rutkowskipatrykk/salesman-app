import app.cash.turbine.test
import com.example.salesmanapp.MainCoroutineRule
import com.example.salesmanapp.feature_search.domain.model.Salesman
import com.example.salesmanapp.feature_search.domain.use_case.GetSalesmanListByArea
import com.example.salesmanapp.feature_search.presentation.search.SearchScreenAction
import com.example.salesmanapp.feature_search.presentation.search.SearchScreenViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getSalesmanListByArea: GetSalesmanListByArea

    private lateinit var tested: SearchScreenViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = SearchScreenViewModel(getSalesmanListByArea)
    }

    @Test
    fun `setSearchValue dispatches action then updates current search query and loading state`() {
        val searchValue = "12345"
        val action = SearchScreenAction.SearchValueChanged(searchValue)

        tested.dispatchAction(action)

        assertEquals(tested.isLoading.value, true)
        assertEquals(tested.searchValue.value, searchValue)
    }

    @Test
    fun `dispatch action then call getSalesmanListByArea and add values to list and update loading state`() =
        runTest {
            // Given
            val searchValue = "2345"
            val action = SearchScreenAction.SearchValueChanged(searchValue)
            val salesmanList = listOf(
                Salesman(
                    name = "AAA",
                    areas = listOf("12345")
                )
            )
            every { getSalesmanListByArea(any()) } returns flowOf(salesmanList)

            // When
            tested.dispatchAction(action)

            // Then
            tested.searchFlow.test {
                assertEquals(searchValue, awaitItem())
                delay(1100) // Wait for debounce time
                verify(exactly = 1) { getSalesmanListByArea(searchValue) }
                assertEquals(tested.isLoading.value, false)
                assertEquals(tested.salesmanList.toList(), salesmanList)
            }
        }
}