package com.creativegrpcx.perawatcher.data.repository.source

import com.creativegrpcx.perawatcher.data.repository.dao.TransactionDao
import com.creativegrpcx.perawatcher.data.repository.dao.WalletDao
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao, private val walletDao: WalletDao
) : IDataRepository.ILocalDataSource {

    override suspend fun insertTransaction(vararg transaction: Transaction) : List<Long> {
       return transactionDao.insertTransaction(*transaction)
    }

    override suspend fun deleteTransaction(vararg transaction: Transaction) : Int {
       return transactionDao.deleteTransaction(*transaction)
    }

    override suspend fun updateTransaction(vararg transaction: Transaction): Int {
       return transactionDao.updateTransaction(*transaction)
    }

    override  fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions()
    }

    override suspend fun getTransaction(transactionId: Int): Flow<Transaction> {
        return transactionDao.getTransaction(transactionId)
    }

    override suspend fun insertWallet(vararg wallet: Wallet) : List<Long> {
       return walletDao.insertWallet(*wallet)
    }

    override suspend fun deleteWallet(vararg wallet: Wallet) : Int {
      return  walletDao.deleteWallet(*wallet)
    }

    override suspend fun updateWallet(vararg wallet: Wallet) : Int {
      return  walletDao.updateWallet(*wallet)
    }

    override suspend fun getAllWallet(): Flow<List<Wallet>> {
       return walletDao.getAllWallet()
    }

    override suspend fun getWallet(walletId: Int): Flow<Wallet> {
       return walletDao.getWallet(walletId)
    }


}
