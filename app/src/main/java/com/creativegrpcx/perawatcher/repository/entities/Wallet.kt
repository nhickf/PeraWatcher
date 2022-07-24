package com.creativegrpcx.perawatcher.repository.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.creativegrpcx.perawatcher.types.WalletType
import java.util.*

@Entity(tableName = "wallet")
data class Wallet(
    val walletName : String,
    val walletAmount : Float,
    val walletType : WalletType,
    val isEnabled : Boolean,
    val isPrimary : Boolean

){
    @PrimaryKey(autoGenerate = false) var walletId : String = UUID.randomUUID().toString()
}
