package com.example.mandiritest.model

import java.io.Serializable

data class Article(
    val source: NewsSource?,
    val author: String,
    val title: String,
    val url: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Serializable
