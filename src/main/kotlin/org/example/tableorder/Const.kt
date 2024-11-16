package org.example.tableorder

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Const {
    companion object{

        val DATE_FORMAT = "yyyy-MM-dd HH:mm"

        fun convertStringToDate(dateString: String): LocalDateTime {
            val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
            return LocalDateTime.parse(dateString, formatter)
        }

        fun isWithin30Min(targetDateTime: LocalDateTime): Boolean {
            val now = LocalDateTime.now()

            val duration = Duration.between(now, targetDateTime)
            val differenceInMinutes = duration.toMinutes()

            // ±30분 이내인지 확인
            return differenceInMinutes in -30..30
        }
    }
}