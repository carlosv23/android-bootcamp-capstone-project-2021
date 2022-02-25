package com.example.cryptochallenge.data.datasource.room.dao

import androidx.room.*
import com.example.cryptochallenge.domain.model.TickerData
import io.reactivex.Single

@Dao
interface TickerDataDao {

    @Query("SELECT * FROM tickerData WHERE book = :book")
    fun getTickerDataByBook(book: String): Single<TickerData>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tickerData: TickerData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tickerData: List<TickerData>)

    @Delete
    fun delete(tickerData: TickerData)
}
