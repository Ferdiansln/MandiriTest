package com.example.mandiritest.core.datasource

import com.example.mandiritest.model.ApiResponse
import com.example.mandiritest.model.ArticleResponse
import com.example.mandiritest.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemoteDataSource {

    @GET("/v2/sources")
    suspend fun getSources(
        @Query("country") country: String? = null,
        @Query("category") category: String? = null,
        @Query("language") language: String? = null,
    ): ApiResponse<SourceResponse>

    @GET("/v2/everything")
    suspend fun getArticles(
        @Query("q") q: String? = null,
        @Query("qInTitle") qInTitle: String? = null,
        @Query("sources") sources: String? = null,
        @Query("domains") domains: String? = null,
        @Query("excludeDomains") excludeDomains: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("language") language: String? = null,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): ApiResponse<ArticleResponse>
}
