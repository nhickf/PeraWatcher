package com.creativegrpcx.perawatcher.domain.model.state

import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction

data class WalletState(
    override val isNavigateUp: Boolean = false,
    override val isLoading: Boolean = false,
    override val error: Error?=null,
    val wallets : List<WalletTransaction> = emptyList(),
    val totalNetWorth : String = "0.00"
): MainState()
