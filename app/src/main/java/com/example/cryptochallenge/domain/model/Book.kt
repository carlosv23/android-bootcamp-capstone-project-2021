package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("book")
    val name: String,
    @SerializedName("minimum_amount")
    val min_amount: Double,
    @SerializedName("maximum_amount")
    val max_amount: Double,
    @SerializedName("minimum_price")
    val min_price: Double,
    @SerializedName("maximum_price")
    val max_price: Double,
    @SerializedName("minimum_value")
    val min_value: Double,
    @SerializedName("maximum_value")
    val max_value: Double
)