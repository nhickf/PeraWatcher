package com.creativegrpcx.perawatcher.dependencyInjector

import android.app.Application
import android.text.Editable
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.creativegrpcx.perawatcher.BaseActivity
import com.creativegrpcx.perawatcher.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

}
