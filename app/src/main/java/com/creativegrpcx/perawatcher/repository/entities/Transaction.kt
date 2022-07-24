package com.creativegrpcx.perawatcher.repository.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.creativegrpcx.perawatcher.data.DateTime
import com.creativegrpcx.perawatcher.types.CategoryType
import com.creativegrpcx.perawatcher.types.WalletType
import java.util.*

@Entity(tableName = "transactions")
data class Transaction(
    val title : String,
    val category : CategoryType,
    val amount : Float,
    val date: String,
    val time: String,
    val walletId : String,

) {
    @PrimaryKey(autoGenerate = false) var transactionId : String = UUID.randomUUID().toString()
}
