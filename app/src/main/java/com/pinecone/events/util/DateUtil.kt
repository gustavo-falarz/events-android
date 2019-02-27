package com.pinecone.events.util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

object DateUtil {

    fun String.toDate(pattern: Pattern = Pattern.DEFAULT): Date = this.toDateTime(pattern)

    private fun String.toDateTime(pattern: Pattern = Pattern.DEFAULT): Date {
        return getFormatter(pattern).parseDateTime(this).toDate()
    }

    fun Date.formatToString(pattern: Pattern = Pattern.DEFAULT): String {
        val dateTime = DateTime(this)
        return getFormatter(pattern).print(dateTime)
    }

    enum class Pattern(val pattern: String) {
        DEFAULT("dd/MM/yyyy HH:mm:ss"),
        EVENT_DEFAULT("dd/MM/yyyy HH:mm"),
        EVENT_TIME("HH:mm"),
        EVENT_DATE("dd/MM/yyyy"),
        DATA_BASE("yyyy-MM-dd HH:mm"),
    }

    private fun getFormatter(pattern: Pattern = Pattern.DEFAULT) = DateTimeFormat.forPattern(pattern.pattern)


}