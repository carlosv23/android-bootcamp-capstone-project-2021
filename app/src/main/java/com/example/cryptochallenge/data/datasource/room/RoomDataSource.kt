package com.example.cryptochallenge.data.datasource.room

import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.TickerData
import io.reactivex.Single
import java.util.*

interface RoomDataSource {

    fun getAllBooks(): Single<List<Book>>?
    fun insertAll(books: List<Book>)

    fun getTickerDataByBook(book: String): Single<TickerData>?
    fun insertTickerData(tickerData: TickerData)

    fun getOrders(book: String): Single<List<Order>>?

    fun insertOrders(askList: List<Order>, bidList: List<Order>)
    fun getAskOrders(book: String): Single<List<Order>>?
    fun removeAskOrders(book: String)
    fun getBidOrders(book: String): Single<List<Order>>?
    fun removeBidOrders(book: String)
    fun removeOrders(book: String)
}
