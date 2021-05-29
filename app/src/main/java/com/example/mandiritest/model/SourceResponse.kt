package com.example.mandiritest.model

import java.io.Serializable

data class SourceResponse(
    val status: String,
    val sources: List<NewsSource>?
): Serializable
