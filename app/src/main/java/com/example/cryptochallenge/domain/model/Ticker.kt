package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("payload")
    val tickerData: TickerData
)
