package com.pinecone.events.util

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.util.*

object DateUtil {

    fun String.toDateTime(pattern: Pattern = Pattern.DEFAULT): DateTime {
        return getFormatter(pattern).parseDateTime(this)
    }

    fun Date.formatToString(pattern: Pattern = Pattern.DEFAULT): String {
        val dateTime = DateTime(this)
        return getFormatter(pattern).print(dateTime)
    }

    fun createDate(dateStr: String, pattern: Pattern = Pattern.EVENT_DEFAULT): DateTime {
        val initialDate = dateStr.toDateTime(pattern)
        val utc = initialDate.withZone(DateTimeZone.UTC)
        val local = utc.toLocalDateTime()
        return local.toDateTime()
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