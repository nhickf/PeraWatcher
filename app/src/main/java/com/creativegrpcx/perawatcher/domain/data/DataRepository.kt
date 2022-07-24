package com.creativegrpcx.perawatcher.domain.data

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.controller.InsertTransaction
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(private val localDataSource: IDataRepository.ILocalDataSource) {

     suspend fun insertTransaction(vararg transaction: Transaction , onComplete : () -> Unit) {
        InsertTransaction(localDataSource)(transactions = transaction, onComplete =  onComplete)
    }

     suspend fun deleteTransaction(vararg transaction: Transaction) {
        localDataSource.deleteTransaction(*transaction)
    }

     suspend fun updateTransaction(vararg transaction: Transaction) {
        localDataSource.updateTransaction(*transaction)
    }

     suspend fun getTransactions(vararg categories: CategoryType): Flow<List<Transaction>>{
        //vararg categories: CategoryType
//        val allTransaction : Flow<List<Transaction>> = transactionDao.getAllTransactions()
//        if (categories.isEmpty()){
//            return allTransaction
//            return allTransaction.map { transactions ->
//                if (categories.isNotEmpty()){
//                    return@map transactions.filter { transaction -> categories.contains(transaction.category) }
//                }
//                transactions
//            }
//            return allTransaction.map { list: List<Transaction> -> list.filter { transaction -> categories.contains(transaction.category) } }
       return localDataSource.getTransactions()
    }

     suspend fun getTransaction(transactionId: Int) : Flow<Transaction> {
       return localDataSource.getTransaction(transactionId)
    }

     suspend fun insertWallet(vararg wallet: Wallet) {
        localDataSource.insertWallet(*wallet)
    }

     suspend fun deleteWallet(vararg wallet: Wallet){
        localDataSource.deleteWallet(*wallet)
    }

      suspend fun updateWallet(vararg wallet: Wallet) {
        localDataSource.updateWallet(*wallet)
    }

     suspend fun getAllWallet(): Flow<List<Wallet>>{
       return localDataSource.getAllWallet()
    }

     suspend fun getWallet(walletId: Int) : Flow<Wallet> {
       return localDataSource.getWallet(walletId)
    }
}
