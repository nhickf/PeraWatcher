package com.creativegrpcx.perawatcher.data.repository.entities

import androidx.room.Embedded
import androidx.room.Relation

data class WalletTransaction(

    @Embedded val wallet: Wallet,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "walletId"
    )

    val transaction: List<Transaction>

)
