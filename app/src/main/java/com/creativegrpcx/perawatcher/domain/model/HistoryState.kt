package com.creativegrpcx.perawatcher.domain.model

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction

data class HistoryState(
    override val isLoading: Boolean = false,
    override val error: Error? = null,
    override val isNavigateUp: Boolean = false,
    val groupTransactions: Map<String, List<Transaction>> = emptyMap(),
    val overAllExpenses : String = "0.00"
) : MainState()
