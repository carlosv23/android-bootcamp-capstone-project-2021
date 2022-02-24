package com.example.cryptochallenge.domain.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "order", primaryKeys = ["book", "price", "amount"])
data class Order(
    @SerializedName("book")
    val book: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("amount")
    val amount: Double,
    var isAsk: Boolean
)
