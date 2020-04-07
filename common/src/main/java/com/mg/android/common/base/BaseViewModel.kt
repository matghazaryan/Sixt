package com.mg.android.common.base

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.mg.android.common.utils.Event
import com.mg.android.navigation.NavigationCommand
import com.mg.android.repository.AppDispatchers
import com.mg.android.repository.utils.Resource
import com.mg.android.common.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Observer

abstract class BaseViewModel() : ViewModel() {

    // FOR ERROR HANDLER
    private val _snackbarError = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackbarError

    // FOR NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation
    private var status = MediatorLiveData<Resource.Status>()
    private val statusObserver: androidx.lifecycle.Observer<Resource.Status> =
        androidx.lifecycle.Observer {}

    init {
        status.observeForever { statusObserver }//statusObserver's onChanged() method won't be  called without active observer
    }

    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }

    fun <T> handleRequest(
        dispatchers: AppDispatchers,
        request: suspend () -> LiveData<Resource<T>>,
        callback: (LiveData<Resource<T>>) -> Unit
    ) {
        viewModelScope.launch(dispatchers.main) {
            val result: LiveData<Resource<T>> = withContext(dispatchers.io) {
                request()
            }
            status.addSource(Transformations.map(result) { _result -> _result.status }) {_status->
                status.value = _status
                handleError(_status)
            }
            callback(result)

        }

//        viewModelScope.launch(dispatchers.main) {
//            val result: LiveData<Resource<T>> = withContext(dispatchers.io) {
//                request()
//            }
//            status.removeObserver(statusObserver)
//            status = Transformations.map(result) {
//                it.status
//            }
//            status.observeForever(statusObserver)
//            callback(result)
//
//        }
    }

    private fun handleError(status: Resource.Status) {
        if (status == Resource.Status.ERROR) _snackbarError.value =
            Event(R.string.an_error_happened)
    }

    override fun onCleared() {
        super.onCleared()
        status.removeObserver(statusObserver)
    }
}