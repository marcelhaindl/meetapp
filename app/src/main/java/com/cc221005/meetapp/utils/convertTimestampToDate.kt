package com.cc221005.meetapp.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale

/**
 * # Convert Timestamp to Formatted Date
 * Is used to convert a firebase timestamp to a formatted date string "Jan 1, 2024"
 *
 * @param timestamp (Timestamp) Firebase Timestamp to be formatted
 * @return formatted date string
 */
fun convertTimestampToFormattedDate(timestamp: Timestamp): String {
    return try {
        val date = timestamp.toDate()
        val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
        dateFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

/**
 * # Convert LocalDateTime to Timestamp
 * Used to convert [localDateTime] into a firebase timestamp.
 *
 * @param localDateTime (LocalDateTime) Date to be converted
 * @return firebase timestamp
 */
fun convertLocalDateTimeToTimestamp(localDateTime: LocalDateTime): Timestamp {
    val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    return Timestamp(date)
}

/**
 * # Convert Timestamp to LocalDateTime
 * Used to convert [timestamp] into a local date time.
 *
 * @param timestamp (Timestamp) Firebase timestamp to be converted
 * @return converted LocalDateTime
 */
fun convertTimestampToLocalDateTime(timestamp: Timestamp): LocalDateTime {
    val instant: Instant = Instant.ofEpochMilli(timestamp.seconds * 1000 + timestamp.nanoseconds / 1_000_000)
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}