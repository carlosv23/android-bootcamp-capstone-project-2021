package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class AvailableBooks(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("payload")
    val books: List<Book>
)
