package com.example.mandiritest.core.usecase

import com.example.mandiritest.core.repository.NewsRemoteRepository
import com.example.mandiritest.model.ApiResponse
import com.example.mandiritest.model.ArticleResponse
import com.example.mandiritest.model.SourceResponse
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: NewsRemoteRepository) {
    suspend fun getSources(
        country: String?, category: String?, language: String?
    ): ApiResponse<SourceResponse> {
        return repository.getSources(country, category, language)
    }

    suspend fun getArticles(
        q: String? = null,
        qInTitle: String? = null,
        sources: List<String>? = null,
        domains: List<String>? = null,
        excludeDomains: List<String>? = null,
        from: String? = null,
        to: String? = null,
        language: String? = null,
        page: Int,
        pageSize: Int
    ): ApiResponse<ArticleResponse> {
        return repository.getArticles(
            q,
            qInTitle,
            sources,
            domains,
            excludeDomains,
            from,
            to,
            language,
            page,
            pageSize
        )
    }
}
