package com.creativegrpcx.perawatcher.domain.model

import com.creativegrpcx.perawatcher.data.repository.entities.Wallet

data class WalletState(
    override val isNavigateUp: Boolean = false,
    override val isLoading: Boolean = false,
    override val error: Error?=null,
    val wallets : List<Wallet> = emptyList()
):MainState()
