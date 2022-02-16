package com.example.cryptochallenge.data.datasource.remote

import com.example.cryptochallenge.data.service.BitsoService
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.PayloadOrder
import com.example.cryptochallenge.domain.model.TickerData
import io.reactivex.Single
import javax.inject.Inject

class RemoteBitsoDataSourceImpl @Inject constructor(
    private val service: BitsoService
): RemoteBitsoDataSource {
    override fun getAvailableBooks(): Single<List<Book>> {
        return service.getAvailableBooks().map { it.books }
    }

    override fun getTicker(book: String): Single<TickerData> {
        return service.getTicker(book).map { it.tickerData }
    }

    override fun getOrderBook(book: String): Single<PayloadOrder> {
        return service.getOdrderBook(book).map { it.payload }
    }
}