package com.creativegrpcx.perawatcher.dependencyInjector

import android.app.Application
import com.creativegrpcx.perawatcher.data.repository.source.AppDatabase
import com.creativegrpcx.perawatcher.data.repository.source.LocalDataSourceImpl
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import com.creativegrpcx.perawatcher.data.repository.dao.TransactionDao
import com.creativegrpcx.perawatcher.data.repository.dao.WalletDao
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

    @Provides
    @Singleton
    fun providesLocalDataSource(
        transactionDao: TransactionDao , walletDao: WalletDao
    ) = LocalDataSourceImpl(transactionDao, walletDao)

    @Provides
    @Singleton
    fun providesUserRepository(
        localDataSource: LocalDataSourceImpl
    ) = DataRepository(localDataSource)


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
