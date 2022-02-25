package com.example.cryptochallenge.data.datasource.remote

import com.example.cryptochallenge.data.service.BitsoService
import com.example.cryptochallenge.domain.model.*
import io.reactivex.Single
import javax.inject.Inject

class RemoteBitsoDataSourceImpl @Inject constructor(
    private val service: BitsoService
) : RemoteBitsoDataSource {
    override fun getAvailableBooks(): Single<AvailableBooks>? {
        return service.getAvailableBooks()
    }

    override fun getTicker(book: String): Single<TickerData>? {
        return service.getTicker(book)?.map { it.tickerData }
    }

    override fun getOrderBook(book: String): Single<PayloadOrder>? {
        return service.getOdrderBook(book)?.map { it.payload }
    }
}
