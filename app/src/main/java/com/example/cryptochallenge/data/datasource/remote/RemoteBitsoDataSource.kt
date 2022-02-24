package com.example.cryptochallenge.data.datasource.remote

import com.example.cryptochallenge.domain.model.*
import io.reactivex.Single

interface RemoteBitsoDataSource {
    fun getAvailableBooks(): Single<AvailableBooks>?
    fun getTicker(book: String): Single<TickerData>?
    fun getOrderBook(book: String): Single<PayloadOrder>?
}
