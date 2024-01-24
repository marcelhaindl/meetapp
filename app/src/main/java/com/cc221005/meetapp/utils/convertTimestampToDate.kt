package com.cc221005.meetapp.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
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

fun convertLocalDateTimeToTimestamp(localDateTime: LocalDateTime): Timestamp {
    val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    return Timestamp(date)
}

fun convertTimestampToLocalDateTime(timestamp: Timestamp): LocalDateTime {
    val instant: Instant = Instant.ofEpochMilli(timestamp.seconds * 1000 + timestamp.nanoseconds / 1_000_000)
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}