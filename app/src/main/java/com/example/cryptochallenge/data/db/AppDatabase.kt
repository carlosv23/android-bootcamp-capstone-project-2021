package com.example.cryptochallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptochallenge.data.datasource.room.dao.BookDao
import com.example.cryptochallenge.data.datasource.room.dao.OrderDao
import com.example.cryptochallenge.data.datasource.room.dao.TickerDataDao
import com.example.cryptochallenge.domain.model.Book
import com.example.cryptochallenge.domain.model.Order
import com.example.cryptochallenge.domain.model.TickerData

@Database(entities = [Book::class, Order::class, TickerData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao
    abstract fun getOrderDao(): OrderDao
    abstract fun getTickerDataDao(): TickerDataDao
}
