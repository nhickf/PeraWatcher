package com.creativegrpcx.perawatcher.dependencyInjector

import com.creativegrpcx.perawatcher.BaseActivity
import com.creativegrpcx.perawatcher.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [],modules = [(ApplicationModule::class),
    (RepositoryModule::class) , (ViewModelModule::class)])
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)

}
