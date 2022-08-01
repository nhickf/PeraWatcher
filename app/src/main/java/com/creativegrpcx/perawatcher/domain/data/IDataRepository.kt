package com.creativegrpcx.perawatcher.domain.data

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    interface ILocalDataSource{
        suspend fun insertTransaction(vararg transaction: Transaction) : List<Long>
        suspend fun deleteTransaction(vararg transaction: Transaction) : Int
        suspend fun updateTransaction(vararg transaction: Transaction) : Int
        suspend fun getTransactions() : Flow<List<Transaction>>
        suspend fun getTransaction(transactionId: Int) : Flow<Transaction>

        suspend fun insertWallet(vararg wallet: Wallet) : List<Long>
        suspend fun deleteWallet(vararg wallet: Wallet) : Int
        suspend fun updateWallet(vararg wallet: Wallet) : Int
        suspend fun getAllWallet() : Flow<List<Wallet>>
        suspend fun getWallet(walletId: Int) :  Flow<Wallet>
    }

    interface INetworkDataSource{

    }
}
