package com.creativegrpcx.perawatcher.domain.model.state

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction
import com.creativegrpcx.perawatcher.domain.model.Date
import com.creativegrpcx.perawatcher.domain.model.Time
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.utils.Constants

data class AddTransactionState (
    val titleValue : String = "",
    val selectedCategory : CategoryType? = null,
    val expensesAmount : String = "",
    val walletId : String = "",
    val date : Date = Constants.currentDate,
    val time : Time = Constants.currentTime,
    val extraNotes : String = "",
    val wallet : List<WalletTransaction> = emptyList()
)

fun AddTransactionState.transformToTransaction() : Transaction =
    Transaction(
        title = titleValue,
        category = selectedCategory ?: CategoryType.Others,
        amount = expensesAmount,
        date = date.formattedDate,
        time = time.formattedTime,
        notes = extraNotes,
        walletId = walletId,
    )