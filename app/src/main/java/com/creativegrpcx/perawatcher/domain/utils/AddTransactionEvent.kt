package com.creativegrpcx.perawatcher.domain.utils

import com.creativegrpcx.perawatcher.domain.model.Date
import com.creativegrpcx.perawatcher.domain.model.Time
import com.creativegrpcx.perawatcher.domain.types.CategoryType

sealed interface AddTransactionEvent{
    data class TitleChange(val title : String) : AddTransactionEvent
    data class AmountChange(val amount : String) : AddTransactionEvent
    data class CategoryChange(val categoryType: CategoryType) : AddTransactionEvent
    data class DateChange(val date : Date) : AddTransactionEvent
    data class TimeChange(val time : Time) : AddTransactionEvent
    data class NoteChange(val note : String) : AddTransactionEvent
    object SaveTransaction : AddTransactionEvent
}