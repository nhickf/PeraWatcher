package com.creativegrpcx.perawatcher.data.repository.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.creativegrpcx.perawatcher.domain.types.WalletType
import com.creativegrpcx.perawatcher.ui.utils.formatDecimalSeparator
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import java.util.*

@Entity(tableName = "wallet")
data class Wallet(
    val walletName : String,
    val walletAmount : String,
    val walletType : WalletType,
    val isEnabled : Boolean,
    val isPrimary : Boolean

){
    @PrimaryKey(autoGenerate = false) var walletId : String = UUID.randomUUID().toString()


    @Ignore
    val formatWalletAmount = "$${this.walletAmount.removeComma().toString().formatDecimalSeparator()}"

}
