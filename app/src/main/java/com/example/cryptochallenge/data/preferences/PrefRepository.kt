package com.example.cryptochallenge.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class PrefRepository(val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("DBPreferences", Context.MODE_PRIVATE)

    private val editor = pref.edit()

    fun isLastUpdateGreaterThanOneDay(): Boolean {
        val lastUpdate = (pref.getLong("lastUpdate", 1L) / 1000) / 60
        val date = (System.currentTimeMillis() / 1000) / 60
        val result = date - lastUpdate
        // 9223372036854775807
        Log.d("Test", "Date: " + date.toString())
        Log.d("Test", "LastUpdate: " + lastUpdate.toString())
        Log.d("Test", "result: $result")
        return result > 3600
    }

    /**
     * -1 = Does not exist in DB
     * 0 = is updated
     * 1 = is not updated and must change rows in DB
     */

    fun isDataUpdated(book: String): Int {
        val lastUpdate = (pref.getLong(book, 1L) / 1000) / 60
        val date = (System.currentTimeMillis() / 1000) / 60
        val result = date - lastUpdate
        // 9223372036854775807
        Log.d("Test", "Date: " + date.toString())
        Log.d("Test", "LastUpdate: " + lastUpdate.toString())
        Log.d("Test", "result: $result")
        if(lastUpdate == 0L){ return -1}
        else if(result > 3600) {return 1}
        else return 0
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
