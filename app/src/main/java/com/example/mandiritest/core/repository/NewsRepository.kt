package com.example.mandiritest.core.repository

import com.example.mandiritest.core.datasource.NewsRemoteDataSource
import com.example.mandiritest.model.ApiResponse
import com.example.mandiritest.model.ArticleResponse
import com.example.mandiritest.model.SourceResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRemoteRepository {
    override suspend fun getSources(country: String?, category: String?, language: String?): ApiResponse<SourceResponse> {
        return newsRemoteDataSource.getSources(country, category, language)
    }

    override suspend fun getArticles(
        q: String?,
        qInTitle: String?,
        sources: List<String>?,
        domains: List<String>?,
        excludeDomains: List<String>?,
        from: String?,
        to: String?,
        language: String?,
        page: Int,
        pageSize: Int
    ): ApiResponse<ArticleResponse> {
        val sourceString = generateCommaSeparatedString(sources)
        val domainsString = generateCommaSeparatedString(domains)
        val excludeDomainsString = generateCommaSeparatedString(excludeDomains)
        return newsRemoteDataSource.getArticles(
            q,
            qInTitle,
            sourceString,
            domainsString,
            excludeDomainsString,
            from,
            to,
            language,
            page,
            pageSize
        )
    }

    private fun generateCommaSeparatedString(list: List<String>?): String? {
        if (list.isNullOrEmpty()) return null
        val sb = StringBuilder()
        for (str in list) {
            sb.append("$str,")
        }
        return sb.removeSuffix(",").toString()
    }
}

interface NewsRemoteRepository {
    suspend fun getSources(country: String?, category: String?, language: String?): ApiResponse<SourceResponse>
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
    ): ApiResponse<ArticleResponse>
}
