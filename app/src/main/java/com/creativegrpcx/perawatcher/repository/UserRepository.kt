package com.creativegrpcx.perawatcher.repository

import com.creativegrpcx.perawatcher.repository.entities.Transaction
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.types.CategoryType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val localDataSource:LocalDataSource):
    IUserRepository.ILocalDataSource,
    IUserRepository.INetworkDataSource {

    override suspend fun insertTransaction(vararg transaction: Transaction) = withContext(
        Dispatchers.Default){
        localDataSource.insertTransaction(*transaction)
    }

    override suspend fun deleteTransaction(vararg transaction: Transaction) = withContext(
        Dispatchers.Default){
        localDataSource.deleteTransaction(*transaction)
    }

    override suspend fun updateTransaction(vararg transaction: Transaction) = withContext(Dispatchers.Default) {
        localDataSource.updateTransaction(*transaction)
    }

    override suspend fun getTransactions(vararg categories: CategoryType): Flow<List<Transaction>> = withContext(Dispatchers.Default) {
        localDataSource.getTransactions(*categories)
    }

    override suspend fun getTransaction(transactionId: Int) : Flow<Transaction> = withContext(Dispatchers.Default){
        localDataSource.getTransaction(transactionId)
    }

    override suspend fun insertWallet(vararg wallet: Wallet)= withContext(Dispatchers.Default) {
        localDataSource.insertWallet(*wallet)
    }

    override suspend fun deleteWallet(vararg wallet: Wallet)= withContext(Dispatchers.Default) {
        localDataSource.deleteWallet(*wallet)
    }

    override  suspend fun updateWallet(vararg wallet: Wallet)= withContext(Dispatchers.Default) {
        localDataSource.updateWallet(*wallet)
    }

    override suspend fun getAllWallet(): Flow<List<Wallet>> = withContext(Dispatchers.Default){
        localDataSource.getAllWallet()
    }

    override suspend fun getWallet(walletId: Int) : Flow<Wallet> = withContext(Dispatchers.Default) {
        localDataSource.getWallet(walletId)
    }
}
