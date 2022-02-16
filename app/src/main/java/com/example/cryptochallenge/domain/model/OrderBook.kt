package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class OrderBook(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("payload")
    val payload: PayloadOrder
)
