package com.example.cryptochallenge.data.repository

import android.util.Log
import com.example.cryptochallenge.data.datasource.remote.RemoteBitsoDataSource
import com.example.cryptochallenge.data.datasource.room.RoomDataSource
import com.example.cryptochallenge.data.preferences.PrefRepository
import com.example.cryptochallenge.di.OnlineState
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.PayloadOrder
import com.example.cryptochallenge.domain.model.TickerData
import com.example.cryptochallenge.domain.model.exceptions.DBDataNotFound
import io.reactivex.Single
import java.io.IOException
import java.util.*
import javax.inject.Inject

class BitsoRepositoryImpl @Inject constructor(
    private val remoteBitsoDataSource: RemoteBitsoDataSource,
    private val roomDataSource: RoomDataSource,
    private val sharedPreferences: PrefRepository,
    private val onlineState: OnlineState
) : BitsoRepository {

    override fun getAvailableBooks(): Single<List<Book>>? {
        //TODO: Remove harcoded strings... "lastUpdate" and "_book"
        val isUpdated = sharedPreferences.isDataUpdated("lastUpdate")
        val isOnline = onlineState.isOnline()
        if (isOnline && (isUpdated == 1 || isUpdated == -1)) {
            sharedPreferences.updatelLastDate()
            Log.d("Test", "NETWORK CALL***********************")
            return remoteBitsoDataSource.getAvailableBooks()?.map {
                roomDataSource.insertAll(it.books)
                it.books
            }
        } else if (!isOnline && isUpdated == -1) {
            return Single.error(DBDataNotFound())
        } else {
            Log.d("Test", "DATABASE (ROOM)***********************")
            return roomDataSource.getAllBooks()
        }
    }

    override fun getTicker(book: String): Single<TickerData>? {
        val isUpdated = sharedPreferences.isDataUpdated(book)
        val isOnline = onlineState.isOnline()
        if (isOnline && (isUpdated == 1 || isUpdated == -1)) {
            Log.d("TestTicker", "NETWORK CALL***********************")
            return remoteBitsoDataSource.getTicker(book)?.map {
                roomDataSource.insertTickerData(it)
                it
            }
        } else if (!isOnline && isUpdated == -1) {
            return Single.error(DBDataNotFound())
        } else {
            Log.d("TestTicker", "DATABASE (ROOM)***********************")
            return roomDataSource.getTickerDataByBook(book)
        }

    }

    override fun getOrderBook(book: String): Single<PayloadOrder>? {
        val isUpdated = sharedPreferences.isDataUpdated(book + "_order")
        val isOnline = onlineState.isOnline()
        if (isOnline && (isUpdated == 1 || isUpdated == -1)) {
            sharedPreferences.updateDatalLastDate(book + "_order")
            Log.d("TestOrder", "NETWORK CALL***********************")
            return remoteBitsoDataSource.getOrderBook(book)?.map {
                roomDataSource.removeOrders(book)
                roomDataSource.insertOrders(it.asks, it.bids)
                it
            }
        } else if (!isOnline && isUpdated == -1) {
            return Single.error(DBDataNotFound())
        } else {
            Log.d("TestOrder", "DATABASE (ROOM)***********************")
            return Single.zip(
                roomDataSource.getAskOrders(book),
                roomDataSource.getBidOrders(book),
                { ask, bids ->
                    PayloadOrder(ask, bids)
                }
            )
        }

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
