package com.cc221005.meetapp.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun convertTimestampToFormattedDate(timestamp: com.google.firebase.Timestamp): String {
    return try {
        val date = timestamp.toDate()
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
        dateFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}