package com.example.mandiritest.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mandiritest.core.usecase.NewsUseCase
import com.example.mandiritest.model.ApiErrorResponse
import com.example.mandiritest.model.ApiSuccessResponse
import com.example.mandiritest.model.NewsSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourceListViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    data class ViewState(
        val showLoading: Boolean,
        val newsSources: List<NewsSource>
    )

    sealed class SourceEvent {
        data class OnError(val message: String) : SourceEvent()
        data class OnShowFilter(
            val country: String?,
            val category: String?, val language: String?
        ) : SourceEvent()
    }

    private fun currentViewState(): ViewState = viewState.value!!
    private val _viewState: MutableLiveData<ViewState> =
        MutableLiveData(ViewState(true, emptyList()))
    val viewState: LiveData<ViewState> = _viewState
    private val eventStatus = MutableLiveData<SourceEvent>()
    val observeEvent: LiveData<SourceEvent> = eventStatus

    private var countryId: String? = null
    private var categoryId: String? = null
    private var language: String? = null

    fun onCreateView(category: String) = viewModelScope.launch {
        categoryId = category
        loadData()
    }

    private fun loadData()  = viewModelScope.launch {
        _viewState.value = currentViewState().copy(showLoading = true)
        /**note: so the instruction said to implement endless scroll for source, but they didn't provide the paging system
         * for this endpoint, and the number of sources are limited, so I believe no need for this
         */
        when (val response = newsUseCase.getSources(
            country = countryId, category = categoryId, language = language
        )) {
            is ApiSuccessResponse -> {
                _viewState.value =
                    currentViewState().copy(newsSources = response.body.sources ?: emptyList())

            }
            is ApiErrorResponse -> eventStatus.value = SourceEvent.OnError(response.errorMessage)
        }
        _viewState.value = currentViewState().copy(showLoading = false)
    }

    fun getFilter() {
        eventStatus.value = SourceEvent.OnShowFilter(countryId, categoryId, language)
    }

    fun setFilter(country: String?, category: String?, language: String?) {
        countryId = country
        categoryId = category
        this.language = language
        loadData()
    }
}
