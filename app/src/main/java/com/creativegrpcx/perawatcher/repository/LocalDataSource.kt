package com.creativegrpcx.perawatcher.repository

import androidx.annotation.WorkerThread
import com.creativegrpcx.perawatcher.repository.dao.TransactionDao
import com.creativegrpcx.perawatcher.repository.dao.WalletDao
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.types.CategoryType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext.get

class LocalDataSource @Inject constructor(private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
                                              private val transactionDao: TransactionDao, private val walletDao: WalletDao
) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTransaction(vararg transaction: Transaction){
        this@LocalDataSource.transactionDao.insertTransaction(*transaction)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteTransaction(vararg transaction: Transaction) {
        this@LocalDataSource.transactionDao.deleteTransaction(*transaction)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTransaction(vararg transaction: Transaction) {
        this@LocalDataSource.transactionDao.updateTransaction(*transaction)
    }

    fun getTransaction(transactionId: Int) : Flow<Transaction> = transactionDao.getTransaction(transactionId)

    fun getTransactions(vararg categories: CategoryType): Flow<List<Transaction>>{
        val allTransaction : Flow<List<Transaction>> = transactionDao.getAllTransactions()
        if (categories.isEmpty()){
            return allTransaction
        }
        return allTransaction.map { list: List<Transaction> -> list.filter { transaction -> categories.contains(transaction.category) } }
    }

    //Wallet
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWallet(vararg Wallet: Wallet) {
        this@LocalDataSource.walletDao.insertWallet(*Wallet)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteWallet(vararg Wallet: Wallet) {
        this@LocalDataSource.walletDao.deleteWallet(*Wallet)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWallet(vararg Wallet: Wallet) {
        this@LocalDataSource.walletDao.updateWallet(*Wallet)
    }

    fun getAllWallet() : Flow<List<Wallet>> = walletDao.getAllWallet()

    fun getWallet(walletId: Int) : Flow<Wallet> = walletDao.getWallet(walletId)


}
