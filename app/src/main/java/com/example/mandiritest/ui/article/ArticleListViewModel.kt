package com.example.mandiritest.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mandiritest.core.usecase.NewsUseCase
import com.example.mandiritest.model.ApiErrorResponse
import com.example.mandiritest.model.ApiSuccessResponse
import com.example.mandiritest.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }
    data class ViewState(
        val showLoading: Boolean
    )


    sealed class ArticleEvent {
        data class OnError(val message: String): ArticleEvent()
        data class OnShowFilter(val from: String?,val to: String?,val q: String?,
                                val qInTitle: String?, val language: String?,val sources: ArrayList<String>?): ArticleEvent()
    }
    // filter
    private var from: String? = null
    private var to: String? = null
    private var q: String? = null
    private var qInTitle: String? = null
    private var language: String? = null

    private fun currentViewState(): ViewState = viewState.value!!
    private val _viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState(true))
    private val _articleListLiveData: MutableLiveData<List<Article>> = MutableLiveData(emptyList())
    val articleListLiveData: LiveData<List<Article>> = _articleListLiveData
    val viewState: LiveData<ViewState> = _viewState
    private val eventStatus = MutableLiveData<ArticleEvent>()
    val observeEvent: LiveData<ArticleEvent> = eventStatus
    private var page = 1
    private val list = mutableListOf<Article>()
    private val sourceList = ArrayList<String>()
    private var totalResult = 0

    fun onCreateView(sourceId: String) {
        sourceList.add(sourceId)
        page = 0
        _articleListLiveData.value = list
        loadData()
    }

    fun loadData() = viewModelScope.launch {
        if(totalResult !=0 && (page+1)* DEFAULT_PAGE_SIZE > totalResult) return@launch
        page++
        _viewState.value = currentViewState().copy(showLoading = true)
        when (val response = newsUseCase.getArticles(
            q = q,
            qInTitle = qInTitle,
            from = from,
            to = to,
            language = language,
            sources = sourceList, page = page, pageSize = DEFAULT_PAGE_SIZE)) {
            is ApiSuccessResponse -> {
                if(page == 1) list.clear()
                totalResult = response.body.totalResults
                list.addAll(response.body.articles?: emptyList())
                _articleListLiveData.value = list
            }
            is ApiErrorResponse -> {
                page--
                eventStatus.value = ArticleEvent.OnError(response.errorMessage)
            }
        }
        _viewState.value = currentViewState().copy(showLoading = false)
    }

    fun getFilter() {
        eventStatus.value = ArticleEvent.OnShowFilter(from, to, q, qInTitle, language, sourceList)
    }

    fun setFilter(
        from: String,
        to: String,
        q: String,
        qInTitle: String,
        language: String?,
        sources: String) {
        this.from = if(from.isBlank()) null else from
        this.to = if(to.isBlank()) null else to
        this.q = if(q.isBlank()) null else q
        this.qInTitle = if(qInTitle.isBlank()) null else qInTitle
        this.language = if(language.isNullOrBlank()) null else language
        sourceList.clear()
        if(sources.isNotBlank()) {
            val split = sources.split(",")
            sourceList.addAll(split)
        }
        page = 0
        totalResult = 0
        loadData()
    }
}
