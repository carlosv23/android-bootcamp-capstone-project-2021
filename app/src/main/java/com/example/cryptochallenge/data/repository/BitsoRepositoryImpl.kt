package com.example.cryptochallenge.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSource
import com.example.cryptochallenge.data.datasource.room.RoomDataSource
import com.example.cryptochallenge.data.preferences.PrefRepository
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.PayloadOrder
import com.example.cryptochallenge.domain.model.TickerData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope
import java.util.*
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val remoteBitsoDataSource: RemoteBitsoDataSource,
    private val roomDataSource: RoomDataSource,
    private val sharedPreferences: PrefRepository
) : BitsoRepository {

    override fun getAvailableBooks(): Single<List<Book>>? {
        return if (sharedPreferences.isLastUpdateGreaterThanOneDay()) {
            sharedPreferences.updatelLastDate()
            Log.d("Test", "NETWORK CALL***********************")
            remoteBitsoDataSource.getAvailableBooks()?.map {
                roomDataSource.insertAll(it.books) // add books to DB
                it.books
            } //return books...
        } else {
            Log.d("Test", "DATABASE (ROOM)***********************")
            roomDataSource.getAllBooks()
        }
    }

    override fun getTicker(book: String): Single<TickerData>? {
        //TODO: check if user has internet connection... on if contion
        return if (sharedPreferences.isDataUpdated(book)) {
            sharedPreferences.updateDatalLastDate(book)
            Log.d("TestTicker", "NETWORK CALL***********************")
            remoteBitsoDataSource.getTicker(book)?.map {
                roomDataSource.insertTickerData(it)
                it
            }
        } else {
            Log.d("TestTicker", "DATABASE (ROOM)***********************")
            roomDataSource.getTickerDataByBook(book)
        }
    }


    override fun getOrderBook(book: String): Single<PayloadOrder>? {
        return if (sharedPreferences.isDataUpdated(book + "_order")) {
            sharedPreferences.updateDatalLastDate(book + "_order")
            Log.d("TestOrder", "NETWORK CALL***********************")
            remoteBitsoDataSource.getOrderBook(book)?.map {
                roomDataSource.removeOrders(book)
                roomDataSource.insertOrders(it.asks, it.bids)
                it
            }
        } else {
            Log.d("TestOrder", "DATABASE (ROOM)***********************")
            Single.zip(
                roomDataSource.getAskOrders(book),
                roomDataSource.getBidOrders(book),
                {
                    ask, bids -> PayloadOrder(ask, bids)
                }
            )
        }
    }

    override suspend fun getOrderBook2(book: String): Single<List<Order>>? {
        val result = roomDataSource.getOrders(book)
        return result
    }

    override fun updateAvailableBooks(bookList: List<Book>) {
        TODO("Not yet implemented")
    }

    override fun updateTicker(tickerData: TickerData) {
        TODO("Not yet implemented")
    }

    override fun updateOrder(orderList: List<Order>) {
        TODO("Not yet implemented")
    }
}