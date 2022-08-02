package com.creativegrpcx.perawatcher.dependencyInjector

import com.creativegrpcx.perawatcher.ui.activity.ComposeActivity
import com.creativegrpcx.perawatcher.ui.activity.main.BaseActivity
import com.creativegrpcx.perawatcher.ui.fragment.main.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [],modules = [(ApplicationModule::class),
    (RepositoryModule::class) , (ViewModelModule::class)])
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
    fun inject(composeActivity: ComposeActivity)

}
