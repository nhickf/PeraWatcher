package com.creativegrpcx.perawatcher

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModelFactory
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

open class BaseActivity : AppCompatActivity() {

    @Inject lateinit var globalViewModelFactory: GlobalViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).component.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun editable(text :String) : Editable {
        return Editable.Factory.getInstance().newEditable(text)
    }
}