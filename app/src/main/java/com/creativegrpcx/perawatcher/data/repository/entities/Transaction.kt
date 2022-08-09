package com.creativegrpcx.perawatcher.data.repository.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "transactions")
data class Transaction(
    val title: String,
    val category: CategoryType,
    val amount: String,
    val date: String,
    val time: String,
    val walletId: String,
    val notes : String = ""
    ) {
    @PrimaryKey(autoGenerate = false)
    var transactionId: String = UUID.randomUUID().toString()

    @Ignore
    private val formatDate =
        LocalDate.parse(this.date).format(DateTimeFormatter.ofPattern("MMM dd yyyy"))

    @Ignore
    private val formatTime =
        LocalTime.parse(this.time).format(DateTimeFormatter.ofPattern("hh:mm a"))

    @Ignore
    val currentDateTime = "$formatDate - $formatTime"

    @Ignore
    val formatAmount = "$${this.amount.removeComma().toString().formatDecimalSeparator()}"
}
