package com.mg.android.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.mg.android.common.utils.Event
import com.mg.android.common_test.datasets.CarDataset.FAKE_CARS
import com.mg.android.list.domain.GetCarUseCase
import com.mg.android.list.viewmodel.ListViewModel
import com.mg.android.list.views.ListFragmentDirections
import com.mg.android.model.Car
import com.mg.android.navigation.NavigationCommand
import com.mg.android.repository.AppDispatchers
import com.mg.android.repository.utils.Resource
import io.mockk.*
import io.philippeboisney.common_test.extensions.blockingObserve
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class ListUnitTests {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getCarUseCase: GetCarUseCase
    private lateinit var listViewModel: ListViewModel
    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        getCarUseCase = mockk()
    }

    @Test
    fun `requested when ViewModel is created`() {
        val observer = mockk<Observer<Resource<List<Car>>>>(relaxed = true)
        val result = Resource.success(FAKE_CARS)
        coEvery { getCarUseCase() } returns MutableLiveData<Resource<List<Car>>>().apply { value = result }

        listViewModel = ListViewModel(getCarUseCase, appDispatchers)
        listViewModel.cars.observeForever(observer)

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }

    @Test
    fun `requested but failed when ViewModel is created`() {
        val observer = mockk<Observer<Resource<List<Car>>>>(relaxed = true)
        val observerSnackbar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.error(Exception("fail"), null)
        coEvery { getCarUseCase() } returns  MutableLiveData<Resource<List<Car>>>().apply { value = result }

        listViewModel = ListViewModel(getCarUseCase, appDispatchers)
        listViewModel.cars.observeForever(observer)
        listViewModel.snackBarError.observeForever(observerSnackbar)

        verify {
            observer.onChanged(result)
            observerSnackbar.onChanged(listViewModel.snackBarError.value)
        }

        confirmVerified(observer)
    }

    @Test
    fun `clicks on item on RecyclerView`() {
        val event = Event(NavigationCommand.To(ListFragmentDirections.actionListFragmentToDetailFragment(FAKE_CARS.first())))
        coEvery { getCarUseCase() } returns MutableLiveData<Resource<List<Car>>>().apply { value = Resource.success(
            FAKE_CARS) }

        listViewModel = ListViewModel(getCarUseCase, appDispatchers)
        listViewModel.clicksOnItem(FAKE_CARS.first())

        Assert.assertEquals(event.peekContent(), listViewModel.navigation.blockingObserve()!!.peekContent())
    }

    @Test
    fun `refreshes list with swipe to refresh`() {
        val observer = mockk<Observer<Resource<List<Car>>>>(relaxed = true)
        val result = Resource.success(FAKE_CARS)
        coEvery { getCarUseCase() } returns MutableLiveData<Resource<List<Car>>>().apply { value = result }

        listViewModel = ListViewModel(getCarUseCase, appDispatchers)
        listViewModel.cars.observeForever(observer)
        listViewModel.carRefreshesItems()

        verify(exactly = 2) {
            observer.onChanged(result)
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }
}