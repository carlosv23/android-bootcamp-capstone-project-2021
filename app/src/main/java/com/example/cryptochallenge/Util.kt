package com.example.cryptochallenge

import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order

fun Book.getCurrency(): String {
    //TODO: Test with number 2...
    name.split("_")[1].let {
        return it
    }
}

fun Book.getBaseCurrency(): String {
    //TODO: Test with number 2...
    name.split("_")[0].let {
        return it
    }
}

fun Order.getCurrency(): String {
    //TODO: Test with number 2...
    book.split("_")[1].let {
        return it
    }
}

fun Double.formatCurrency(): String {
    return this.toString()
}
