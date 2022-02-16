package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class PayloadOrder(
    @SerializedName("asks")
    val asks: List<Order>,
    @SerializedName("bids")
    val bids: List<Order>
)
