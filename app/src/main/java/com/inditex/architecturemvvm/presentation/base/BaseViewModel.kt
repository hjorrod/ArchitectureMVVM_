package com.inditex.architecturemvvm.presentation.base

import androidx.lifecycle.*
import com.inditex.architecturemvvm.data.util.Event

abstract class BaseViewModel: ViewModel(), LifecycleObserver {
    protected val error by lazy { MutableLiveData<String>() }
    val showErrorEvent: LiveData<Event<String>> = Transformations.map(error) { Event(it) }
}