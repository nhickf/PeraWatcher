package com.creativegrpcx.perawatcher.repository.entities

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.Transaction

data class WalletTransaction(

    @Embedded val wallet: Wallet,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "walletId"
    )

    val transaction: List<Transaction>

)
