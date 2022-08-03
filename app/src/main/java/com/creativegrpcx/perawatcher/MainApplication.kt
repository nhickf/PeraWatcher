package com.creativegrpcx.perawatcher

import android.app.Application
import com.creativegrpcx.perawatcher.dependencyInjector.*

class MainApplication : Application() {

    private val _component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this))
        .repositoryModule(RepositoryModule(this))
        .viewModelModule(ViewModelModule())
        .build()
    val component: ApplicationComponent = _component

}
