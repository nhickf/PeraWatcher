package com.creativegrpcx.perawatcher.domain.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import javax.inject.Inject

class GlobalViewModelFactory @Inject constructor(private val application : Application, private val repository: DataRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GlobalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GlobalViewModel(application,repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
