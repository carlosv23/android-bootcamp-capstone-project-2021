package com.example.cryptochallenge.data.datasource.room.dao

import androidx.room.*
import com.example.cryptochallenge.domain.model.Order
import io.reactivex.Single

@Dao
interface OrderDao {
    @Query("SELECT * FROM `order` WHERE book = 'btc_mxn' AND isAsk = 1 ORDER BY book ")
    fun getAllOrder(): Single<List<Order>>?

    @Query("SELECT * FROM `order` WHERE book = :book AND isAsk = 0 ORDER BY book ")
    fun getBidsByBookName(book: String): Single<List<Order>>?

    @Query("SELECT * FROM `order` WHERE book = :book AND isAsk = 1 ORDER BY book ")
    fun getAskByBookName(book: String): Single<List<Order>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order: Order)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(orderList: List<Order>)

    @Query("DELETE FROM `order` WHERE book = :book")
    fun deleteOrderByBookName(book: String)

    @Query("DELETE FROM `order` WHERE book = :book AND isAsk = 'true'")
    fun deleteAskByBookName(book: String)

    @Query("DELETE FROM `order` WHERE book = :book AND isAsk = 'false'")
    fun deleteBidsByBookName(book: String)


}