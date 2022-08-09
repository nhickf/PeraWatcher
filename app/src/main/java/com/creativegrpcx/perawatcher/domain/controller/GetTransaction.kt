package com.creativegrpcx.perawatcher.domain.controller

import android.util.Log
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.IDataRepository
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.utils.GeneralException
import com.creativegrpcx.perawatcher.domain.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.jvm.Throws

class GetTransaction(private val repository: IDataRepository.ILocalDataSource) {

    suspend operator fun invoke(vararg categories: CategoryType): Flow<Response<List<Transaction>>> {
        val transactionResult = flow {
            emit(
                Response.Loading(
                    isLoading = true,
                    _data = emptyList()
                )
            )
            delay(1000)
             repository.getTransactions().collect {
                emit(
                    Response.Success(
                        _data = if (categories.isEmpty()) {
                            it
                        } else {
                            it.filter { transaction ->
                                categories.contains(transaction.category)
                            }
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
        return transactionResult
    }
}
