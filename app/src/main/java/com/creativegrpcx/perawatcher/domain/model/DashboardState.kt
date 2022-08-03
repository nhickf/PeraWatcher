package com.creativegrpcx.perawatcher.domain.model

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction

data class DashboardState(
    override val isLoading: Boolean = false,
    override val error: Error? = null,
    override val isNavigateUp: Boolean = false,
    val transactions : List<Transaction> = emptyList(),
    val todayExpenses : String = "0.00"
): MainState()
