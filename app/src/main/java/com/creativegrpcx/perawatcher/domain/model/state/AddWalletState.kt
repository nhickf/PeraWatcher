package com.creativegrpcx.perawatcher.domain.model.state

import com.creativegrpcx.perawatcher.domain.types.WalletType

data class AddWalletState(
    val walletName : String = "",
    val walletType: WalletType = WalletType.CASH,
    val walletAmount : String = "",
)


