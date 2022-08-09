package com.creativegrpcx.perawatcher.domain.controller

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.types.enumContains
import com.creativegrpcx.perawatcher.domain.utils.GeneralException

class InsertTransaction(private val repository: IDataRepository.ILocalDataSource) {

    @Throws(GeneralException::class)
    suspend operator fun invoke(vararg transactions: Transaction, onComplete: () -> Unit) {

        if (transactions.isEmpty()) {
            throw GeneralException.EmptyTransaction(
                "Could not save empty transaction."
            )
        }

        transactions.let {
            it.forEach { transaction ->
                when (false) {
                    (transaction.title.isNotEmpty() && transaction.title.isNotBlank()) -> throw GeneralException.IncompleteTransaction(
                        "Please fill the title for this transaction."
                    )
                    (transaction.time.isNotEmpty() && transaction.time.isNotBlank()) -> throw GeneralException.IncompleteTransaction(

                        "Please select time for this transaction."
                    )
                    (transaction.date.isNotEmpty() && transaction.date.isNotBlank()) -> throw GeneralException.IncompleteTransaction(
                        "Please select date for this transaction."
                    )
                    (transaction.walletId.isNotEmpty() && transaction.walletId.isNotBlank()) -> throw GeneralException.IncompleteTransaction(
                        "Please select wallet for this transaction."
                    )
                    else -> {
                        if (repository.insertTransaction(transaction).isEmpty()) {
                            throw GeneralException.FailedExecute("Error occurred unable to save transaction!")
                        }
                        onComplete()
                    }
                }
            }
        }
    }
}
