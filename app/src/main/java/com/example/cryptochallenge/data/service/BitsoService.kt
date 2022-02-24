package com.example.cryptochallenge.data.service

import com.example.cryptochallenge.domain.model.AvailableBooks
import com.example.cryptochallenge.domain.model.OrderBook
import com.example.cryptochallenge.domain.model.Ticker
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoService {
    //TODO:change the object type to Observers...

    @GET("available_books")
    fun getAvailableBooks() : Single<AvailableBooks>?

    @GET("ticker")
    fun getTicker(
        @Query("book") book:String
    ) : Single<Ticker>?

    @GET("order_book")
    fun getOdrderBook(
        @Query("book") book:String
    ) : Single<OrderBook>?

}
