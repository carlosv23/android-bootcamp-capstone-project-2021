package com.example.cryptochallenge.data.datasource.remote

import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.PayloadOrder
import com.example.cryptochallenge.domain.model.TickerData
import io.reactivex.Single

interface RemoteBitsoDataSource {
    fun getAvailableBooks(): Single<List<Book>>
    fun getTicker(book: String): Single<TickerData>
    fun getOrderBook(book: String): Single<PayloadOrder>
}
