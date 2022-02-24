package com.example.cryptochallenge.data.datasource.room

import com.example.cryptochallenge.data.datasource.room.dao.BookDao
import com.example.cryptochallenge.data.datasource.room.dao.OrderDao
import com.example.cryptochallenge.data.datasource.room.dao.TickerDataDao
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.TickerData
import io.reactivex.Single
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val bookDao: BookDao,
    private val orderDao: OrderDao,
    private val tickerDataDao: TickerDataDao
): RoomDataSource {
    override fun getAllBooks(): Single<List<Book>>? {
        return bookDao.getAllBooks()
    }

    override fun insertAll(books: List<Book>) {
        bookDao.insertAll(books)
    }

    override fun getTickerDataByBook(book: String): Single<TickerData>? {
        return tickerDataDao.getTickerDataByBook(book)
    }

    override fun insertTickerData(tickerData: TickerData) {
        tickerDataDao.insert(tickerData)
    }

    override fun insertOrders(askList: List<Order>, bidsList: List<Order>) {
        askList.forEach { a -> a.isAsk = true }
        bidsList.forEach{b -> b.isAsk = false}
        orderDao.insertAll(askList + bidsList)
    }


    override fun getAskOrders(book: String): Single<List<Order>>? {
        return orderDao.getAskByBookName(book)
    }

    override fun getOrders(book: String): Single<List<Order>>? {
        return orderDao.getAllOrder()
    }

    override fun removeAskOrders(book: String) {
        orderDao.deleteAskByBookName(book)
    }

    override fun getBidOrders(book: String): Single<List<Order>>? {
        return orderDao.getBidsByBookName(book)
    }

    override fun removeBidOrders(book: String) {
        orderDao.deleteBidsByBookName(book)
    }

    override fun removeOrders(book: String) {
        orderDao.deleteOrderByBookName(book)
    }


}