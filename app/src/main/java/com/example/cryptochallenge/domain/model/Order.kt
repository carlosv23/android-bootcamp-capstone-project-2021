package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("book")
    val book: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("amount")
    val amount: Double
)
