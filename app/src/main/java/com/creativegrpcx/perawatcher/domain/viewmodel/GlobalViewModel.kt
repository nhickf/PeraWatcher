package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.creativegrpcx.perawatcher.data.repository.entities.Transaction
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.data.repository.entities.SectionedTransaction
import com.creativegrpcx.perawatcher.data.repository.entities.Wallet
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    application: Application,
    private val repository: DataRepository
) : AndroidViewModel(application) {

    private val _uiStateTransaction = MutableStateFlow<List<Transaction>>(arrayListOf())
    private val _uiStateSectionTransaction =
        MutableStateFlow<List<SectionedTransaction>>(arrayListOf())
    private val _uiStateWallet = MutableStateFlow<List<Wallet>>(arrayListOf())


    //public flow
    val uiStateTransaction = _uiStateTransaction.asStateFlow()
    val uiStateSectionedTransaction = _uiStateSectionTransaction.asStateFlow()
    val uiStateWallet = _uiStateWallet.asStateFlow()

    fun loadTransactions(vararg categories: CategoryType) {
        viewModelScope.launch {
            repository.getTransactions(*categories).collect {
                _uiStateTransaction.value = it
            }
        }
    }

    fun loadSectionTransactions() {
        viewModelScope.launch {
            repository.getTransactions().collect {
                _uiStateSectionTransaction.value = CategoryType.values().map {  type ->
                        return@map SectionedTransaction(
                            type.id.toString(),
                            type,
                            it.filter { tt -> tt.category === type })
                }.filter { sectionedTransaction -> sectionedTransaction.sectionItems.isNotEmpty() }
            }
        }
    }

    fun loadWallet() {
        viewModelScope.launch {
            repository.getAllWallet().collect {
                _uiStateWallet.value = it
            }
        }
    }

    fun insertData(vararg transaction: Transaction) {
        viewModelScope.launch {
            try {
                repository.insertTransaction(
                    transaction =  transaction
                ){
                    //TODO : Add view display in insert onComplete
                }
            }catch (e : Exception){
                //TODO : Add view display in insert errors
            }
        }
    }

    fun insertWallet(wallet: Wallet) {
        viewModelScope.launch {
            repository.insertWallet(wallet)
        }
    }
}
