package com.creativegrpcx.perawatcher.dependencyInjector

import android.app.Application
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.domain.viewmodel.GlobalViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesGlobalViewModelFactory (application: Application,
                                        userRepository: DataRepository
    ) = GlobalViewModelFactory(application,userRepository)

}
