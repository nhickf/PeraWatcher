package com.creativegrpcx.perawatcher.domain.controller

import com.creativegrpcx.perawatcher.data.repository.entities.WalletTransaction
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import com.creativegrpcx.perawatcher.domain.utils.Response
import com.creativegrpcx.perawatcher.ui.utils.removeComma
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetWalletTransaction(private val repository: IDataRepository.ILocalDataSource) {

    suspend operator fun invoke(): Flow<Response<List<WalletTransaction>>> {
        val walletTransaction = flow {
            emit(
                Response.Loading(
                    isLoading = true,
                    _data = emptyList()
                )
            )
            repository.getAllWallet().collect { walletTransaction ->
                emit(
                    Response.Success(
                        _data = walletTransaction.map {
                            val expenses = it.transaction.sumOf { tra -> tra.amount.removeComma() }
                            it.copy(
                                wallet = it.wallet.copy(
                                    walletAmount = (it.wallet.walletAmount.removeComma() - expenses).toString()
                                )
                            )
                        }
                    )
                )
            }
        }.catch {
            emit(
                Response.Error(
                    _data = emptyList(),
                    exception = "${it.message}"
                )
            )
        }

        return walletTransaction
    }
}