package com.example.cryptochallenge.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class PrefRepository(val context: Context) {

    //TODO:update reference name...
    private val pref: SharedPreferences = context.getSharedPreferences("DBPreferences", Context.MODE_PRIVATE)

    private val editor = pref.edit()

    private val gson = Gson()

    fun isLastUpdateGreaterThanOneDay(): Boolean{
        val lastUpdate = (pref.getLong("lastUpdate",1L)/1000)/60
        val date = (System.currentTimeMillis()/1000)/60
        val result = date - lastUpdate
        //9223372036854775807
        Log.d("Test","Date: "+date.toString())
        Log.d("Test","LastUpdate: "+lastUpdate.toString())
        Log.d("Test", "result: $result")
        return result > 3600
    }

    fun isDataUpdated(book: String): Boolean{
        val lastUpdate = (pref.getLong(book,1L)/1000)/60
        val date = (System.currentTimeMillis()/1000)/60
        val result = date - lastUpdate
        //9223372036854775807
        Log.d("Test","Date: "+date.toString())
        Log.d("Test","LastUpdate: "+lastUpdate.toString())
        Log.d("Test", "result: $result")
        return result > 3600
    }

    fun updatelLastDate() {
        editor.putLong("lastUpdate", System.currentTimeMillis())
        editor.commit()
    }

    fun updateDatalLastDate(book: String) {
        editor.putLong(book, System.currentTimeMillis())
        editor.commit()
    }
}