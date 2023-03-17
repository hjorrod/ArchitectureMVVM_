package com.inditex.architecturemvvm.presentation.feature.first

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.inditex.architecturemvvm.data.util.Result
import com.inditex.architecturemvvm.domain.mappers.mapToExampleVO
import com.inditex.architecturemvvm.domain.usecase.GetFirstDataExampleUseCase
import com.inditex.architecturemvvm.presentation.base.BaseViewModel
import com.inditex.architecturemvvm.presentation.model.ExampleVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor (
    private val getFirstDataExampleUseCase: GetFirstDataExampleUseCase
): BaseViewModel() {

    /** loadingIndicator display the loading state in screen **/
    private val _loadingIndicator by lazy { MutableLiveData<Boolean>() }
    val loadingIndicator = _loadingIndicator

    private val _examplesList by lazy { MutableLiveData<List<ExampleVO>>() }
    val examplesList = _examplesList

    init {
        loading(true)
        getFirstDataExample()
    }

    private fun getFirstDataExample() {
        getFirstDataExampleUseCase.invoke(
            params = GetFirstDataExampleUseCase.Params("10")
        ) { result ->
            loading(false)
            when (result) {
                is Result.Success ->
                    _examplesList.value = result.data.map { it.mapToExampleVO() }
                is Result.Failure -> {
                    error.value = result.error?.errorInfo?.message
                }
            }
        }
    }

    /** fun to handle the loading state in the view */
    private fun loading(value: Boolean) {
        _loadingIndicator.value = value
    }
}