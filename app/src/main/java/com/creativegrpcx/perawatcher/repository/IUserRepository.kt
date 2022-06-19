package com.creativegrpcx.perawatcher.repository

import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.types.CategoryType
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    interface ILocalDataSource{
        suspend fun insertTransaction(vararg transaction: Transaction)
        suspend fun deleteTransaction(vararg transaction: Transaction)
        suspend fun updateTransaction(vararg transaction: Transaction)
        suspend fun getTransactions(vararg categories : CategoryType) : Flow<List<Transaction>>
        suspend fun getTransaction(transactionId: Int) : Flow<Transaction>

        suspend fun insertWallet(vararg wallet: Wallet)
        suspend fun deleteWallet(vararg wallet: Wallet)
        suspend fun updateWallet(vararg wallet: Wallet)
        suspend fun getAllWallet() : Flow<List<Wallet>>
        suspend fun getWallet(walletId: Int) :  Flow<Wallet>
    }

    interface INetworkDataSource{

    }
}
