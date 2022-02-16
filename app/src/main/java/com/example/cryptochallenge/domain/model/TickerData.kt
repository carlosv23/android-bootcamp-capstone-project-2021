package com.example.cryptochallenge.domain.model

import com.google.gson.annotations.SerializedName

data class TickerData(
    @SerializedName("book")
    val book: String,
    @SerializedName("volume")
    val volume: Double,
    @SerializedName("high")
    val high: Double,
    @SerializedName("last")
    val last: Double,
    @SerializedName("low")
    val low: Double,
    @SerializedName("vwap")
    val vwap: Double,
    @SerializedName("ask")
    val ask: Double,
    @SerializedName("bid")
    val bid: Double
)
