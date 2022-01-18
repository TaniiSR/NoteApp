package com.task.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val DEFAULT_DATE_FORMAT: String = "dd/MM/yyyy"

    fun getCurrentDate(): String? {
        return try {
            val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
            return sdf.format(Date())
        } catch (e: Exception) {
            null
        }
    }
}