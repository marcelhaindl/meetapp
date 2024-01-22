package com.cc221005.meetapp.utils

import java.text.SimpleDateFormat

fun convertTimestampToFormattedDate(timestamp: com.google.firebase.Timestamp): String {
    val date = timestamp.toDate()
    val dateFormat = SimpleDateFormat("MMM d, yyyy")
    return dateFormat.format(date)
}