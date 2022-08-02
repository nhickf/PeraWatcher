package com.creativegrpcx.perawatcher.domain.data

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.controller.GetTransaction
import com.creativegrpcx.perawatcher.domain.controller.InsertTransaction
import com.creativegrpcx.perawatcher.domain.controller.InsertWallet
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
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
         return GetTransaction(localDataSource)(categories = categories)
    }

     suspend fun getTransaction(transactionId: Int) : Flow<Transaction> {
       return localDataSource.getTransaction(transactionId)
    }

     suspend fun insertWallet(vararg wallet: Wallet, onComplete : () -> Unit) {
        InsertWallet(repository = localDataSource)(wallets =  wallet, onComplete =  onComplete)
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
