package com.creativegrpcx.perawatcher.data.repository.entities

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
    val formattedDate = LocalDate.of(year, month, dayOfMonth).format(DateTimeFormatter.ISO_LOCAL_DATE)
}

data class Time(
   val hourOfDay: Int,
   val minute: Int
){
    val formattedTime = LocalTime.of(hourOfDay,minute).format(DateTimeFormatter.ISO_LOCAL_TIME)
}
