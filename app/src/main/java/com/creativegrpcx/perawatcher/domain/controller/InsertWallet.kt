package com.creativegrpcx.perawatcher.domain.controller

import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import com.creativegrpcx.perawatcher.domain.utils.GeneralException
import com.creativegrpcx.perawatcher.ui.utils.removeComma

class InsertWallet(private val repository: IDataRepository.ILocalDataSource) {

    @Throws(GeneralException::class)
    suspend operator fun invoke(vararg wallets: Wallet, onComplete: () -> Unit) {

        if (wallets.isEmpty()) {
            throw GeneralException.EmptyTransaction(
                "Could not save empty wallet."
            )
        }


        wallets.forEachIndexed { _, wallet ->
            when (false) {
                (wallet.walletName.isNotEmpty() && wallet.walletName.isNotBlank()) -> throw GeneralException.IncompleteTransaction(
                    "Please fill the name for this wallet."

                )
                (wallet.walletAmount.removeComma() > 0) -> throw GeneralException.IncompleteTransaction(
                    "Please unable to put initial amount for wallet."
                )
                else -> {
                    if (repository.insertWallet(wallet).isEmpty()) {
                        throw GeneralException.FailedExecute("Error occurred unable to save wallet!")
                    }
                    onComplete()
                }
            }
        }
    }
}
