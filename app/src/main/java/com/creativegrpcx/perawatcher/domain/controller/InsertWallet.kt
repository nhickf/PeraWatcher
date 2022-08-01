package com.creativegrpcx.perawatcher.domain.controller

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.types.WalletType
import com.creativegrpcx.perawatcher.domain.types.enumContains
import com.creativegrpcx.perawatcher.domain.utils.GeneralException

class InsertWallet(private val repository: IDataRepository.ILocalDataSource) {

    @Throws(GeneralException::class)
    suspend operator fun invoke(vararg wallets: Wallet, onComplete: () -> Unit) {

        if (wallets.isEmpty()) {
            throw GeneralException.EmptyTransaction(
                error =  Error(
                    "Could not save empty wallet."
                )
            )
        }


        wallets.forEachIndexed { _, wallet ->
            when (false) {
                (wallet.walletName.isNotEmpty() && wallet.walletName.isNotBlank()) -> throw GeneralException.IncompleteTransaction(
                    error = Error("Please fill the name for this wallet.")

                )
                (wallet.walletAmount > 0) -> throw GeneralException.IncompleteTransaction(
                    error =   Error("Please unable to put initial amount for wallet.")
                )
                else -> {
                    if (repository.insertWallet(wallet).isEmpty()) {
                        throw GeneralException.FailedExecute(Error("Error occurred unable to save wallet!"))
                    }
                    onComplete()
                }
            }
        }

    }
}
