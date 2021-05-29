package com.example.mandiritest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mandiritest.Constant

class MainViewModel : ViewModel() {

    data class ViewState(
        val categories: List<String>
    )

    private fun currentViewState(): ViewState = viewState.value!!
    private val _viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState(emptyList()))
    val viewState: LiveData<ViewState> = _viewState

    init {
        _viewState.value = currentViewState().copy(categories = Constant.CATEGORIES)
    }
}
