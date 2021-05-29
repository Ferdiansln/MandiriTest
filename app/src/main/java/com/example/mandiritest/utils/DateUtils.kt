package com.example.mandiritest.utils

import android.util.Log
import java.lang.Exception
import java.text.SimpleDateFormat

object DateUtils {

    fun convertToReadableTimestamp(dateStr: String): String {
        try {
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = df.parse(dateStr)
            val df2 = SimpleDateFormat("HH:mm dd-MM-yyyy")
            return df2.format(date)
        } catch (e: Exception) {
            Log.e("DateUtils", "Failed to process date", e)
            return ""
        }
    }

    fun getTimestampFromStringYmd(str: String): Long {
        try {
            val df = SimpleDateFormat("yyyy-MM-dd")
            return df.parse(str).time
        } catch (e: Exception) {
            Log.e("DateUtils", "Failed to process date", e)
            return 0
        }
    }

    fun getStringYmdFromTimestamp(timestamp: Long): String {
        try {
            val df = SimpleDateFormat("yyyy-MM-dd")
            return df.format(timestamp)
        } catch (e: Exception) {
            Log.e("DateUtils", "Failed to process date", e)
            return ""
        }
    }
}
