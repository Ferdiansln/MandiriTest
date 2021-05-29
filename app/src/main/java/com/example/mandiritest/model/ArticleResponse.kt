package com.example.mandiritest.model

import java.io.Serializable

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>?
): Serializable
