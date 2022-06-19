package com.creativegrpcx.perawatcher.dependencyInjector

import android.app.Application
import com.creativegrpcx.perawatcher.repository.AppDatabase
import com.creativegrpcx.perawatcher.repository.LocalDataSource
import com.creativegrpcx.perawatcher.repository.UserRepository
import com.creativegrpcx.perawatcher.repository.dao.TransactionDao
import com.creativegrpcx.perawatcher.repository.dao.WalletDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
class RepositoryModule(private val application: Application) {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    fun providesLocalDataSource(
        transactionDao: TransactionDao , walletDao: WalletDao
    ) = LocalDataSource(defaultDispatcher,transactionDao, walletDao)

    @Provides
    @Singleton
    fun providesUserRepository(
        localDataSource: LocalDataSource
    ) = UserRepository(localDataSource)


    @Provides
    @Singleton
    fun provideDatabase() = AppDatabase.getDatabase(application.baseContext ,applicationScope)

    @Singleton
    @Provides
    fun provideTransactionDao(appDatabase: AppDatabase) : TransactionDao {
        return appDatabase.transactionDao()
    }

    @Singleton
    @Provides
    fun provideWalletDao(appDatabase: AppDatabase) : WalletDao {
        return appDatabase.walletDao()
    }

}