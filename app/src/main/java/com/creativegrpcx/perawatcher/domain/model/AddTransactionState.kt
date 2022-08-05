package com.creativegrpcx.perawatcher.domain.model

import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.utils.Constants

data class AddTransactionState (
    val titleValue : String = "",
    val selectedCategory : CategoryType? = null,
    val expensesAmount : String = "0.00",
    val walletId : String = "",
    val date : Date = Constants.currentDate,
    val time : Time = Constants.currentTime,
    val extraNotes : String = ""

)