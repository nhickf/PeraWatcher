package com.creativegrpcx.perawatcher.domain.model

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class DateTime(
    var date : Date?,
    var time: Time?
)

data class Date(
   val year: Int,
   val month: Int,
   val dayOfMonth: Int
) {
    val formattedDate: String = LocalDate.of(year, month + 1, dayOfMonth).format(DateTimeFormatter.ISO_LOCAL_DATE)
}

data class Time(
   val hourOfDay: Int,
   val minute: Int
){
    val formattedTime: String = LocalTime.of(hourOfDay,minute).format(DateTimeFormatter.ISO_LOCAL_TIME)
}
