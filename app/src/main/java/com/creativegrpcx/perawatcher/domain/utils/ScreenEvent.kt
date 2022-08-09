package com.creativegrpcx.perawatcher.domain.utils

import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.model.Date
import com.creativegrpcx.perawatcher.domain.model.Time
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.types.WalletType

sealed interface AddTransactionEvent{
    data class TitleChange(val title : String) : AddTransactionEvent
    data class AmountChange(val amount : String) : AddTransactionEvent
    data class CategoryChange(val categoryType: CategoryType) : AddTransactionEvent
    data class WalletChange(val walletId: String) : AddTransactionEvent
    data class DateChange(val date : Date) : AddTransactionEvent
    data class TimeChange(val time : Time) : AddTransactionEvent
    data class NoteChange(val note : String) : AddTransactionEvent
    data class SaveTransaction(val onComplete : () -> Unit = {} ) : AddTransactionEvent
}

sealed interface AddWalletEvent{
    data class TitleChange(val title : String) : AddWalletEvent
    data class AmountChange(val amount : String) : AddWalletEvent
    data class TypeChange(val type: WalletType) : AddWalletEvent
    data class SaveWallet(val onComplete : () -> Unit = {} ) : AddWalletEvent
}