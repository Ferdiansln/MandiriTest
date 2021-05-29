package com.example.mandiritest.model

import java.io.Serializable

data class NewsSource(
    val id: String,
    val name: String,
    val description: String? = null,
    val url: String? = null,
    val category: String? = null,
    val language: String? = null,
    val country: String? = null
) : Serializable
