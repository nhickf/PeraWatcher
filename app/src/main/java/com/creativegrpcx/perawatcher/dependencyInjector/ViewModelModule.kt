package com.creativegrpcx.perawatcher.dependencyInjector

import android.app.Application
import com.creativegrpcx.perawatcher.repository.LocalDataSource
import com.creativegrpcx.perawatcher.repository.UserRepository
import com.creativegrpcx.perawatcher.viewmodel.GlobalViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesGlobalViewModelFactory (application: Application,
                                        userRepository: UserRepository) = GlobalViewModelFactory(application,userRepository)
}