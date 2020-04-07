package com.mg.android.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mg.android.common.base.BaseViewModel
import com.mg.android.common.utils.Event
import com.mg.android.list.R
import com.mg.android.list.domain.GetCarUseCase
import com.mg.android.list.views.ListFragmentDirections
import com.mg.android.model.Car
import com.mg.android.repository.AppDispatchers
import com.mg.android.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(
    private val getCarUseCase: GetCarUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {
    private val _cars = MediatorLiveData<Resource<List<Car>>>()
    val cars: LiveData<Resource<List<Car>>> get() = _cars
    private var carSource: LiveData<Resource<List<Car>>> = MutableLiveData()

    init {
        getCars()
    }

    fun clicksOnItem(car: Car) = navigate(
        ListFragmentDirections.actionListFragmentToDetailFragment(car)
    )


    fun carRefreshesItems() = getCars()

    private fun getCars() = handleRequest(dispatchers, { getCarUseCase() }, { result ->
        _cars.removeSource(carSource)
        carSource = result
        _cars.addSource(carSource) {
            _cars.value = it
        }
    })

    fun openMapFragment() {
        navigate(ListFragmentDirections.actionListFragmentToDetailFragment(Car()))
    }
}