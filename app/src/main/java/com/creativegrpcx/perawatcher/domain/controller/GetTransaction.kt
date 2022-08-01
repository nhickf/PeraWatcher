package com.creativegrpcx.perawatcher.domain.controller

import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTransaction(private val repository: IDataRepository.ILocalDataSource) {


    suspend operator fun invoke(vararg categories: CategoryType): Flow<List<Transaction>> {
     return repository.getTransactions().map { transactions ->
            if (categories.isEmpty()) {
                 transactions
            } else {
                transactions.filter{ transaction ->
                      categories.contains(transaction.category)
                }
            }
        }
    }

}
