package com.creativegrpcx.perawatcher.dependencyInjector

import com.creativegrpcx.perawatcher.ui.activity.ComposeActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [],modules = [(ApplicationModule::class),
    (RepositoryModule::class) , (ViewModelModule::class)])
interface ApplicationComponent {

    fun inject(composeActivity: ComposeActivity)

}
