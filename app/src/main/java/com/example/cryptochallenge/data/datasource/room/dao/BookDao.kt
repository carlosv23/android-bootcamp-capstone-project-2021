package com.example.cryptochallenge.data.datasource.room.dao

import androidx.room.*
import com.example.cryptochallenge.domain.model.Book
import io.reactivex.Single

@Dao
interface BookDao {

    @Query("SELECT * FROM book ORDER BY name")
    fun getAllBooks(): Single<List<Book>>?

    @Query("SELECT * FROM book WHERE name = :name")
    suspend fun getBookByName(name: String): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<Book>)

    @Delete
    suspend fun delete(book: Book)
}
