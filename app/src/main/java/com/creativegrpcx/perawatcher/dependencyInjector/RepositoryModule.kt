package com.creativegrpcx.perawatcher.dependencyInjector

import android.app.Application
import android.content.Context
import com.creativegrpcx.perawatcher.data.repository.dao.TransactionDao
import com.creativegrpcx.perawatcher.data.repository.dao.WalletDao
import com.creativegrpcx.perawatcher.data.repository.source.AppDatabase
import com.creativegrpcx.perawatcher.data.repository.source.LocalDataSourceImpl
import com.creativegrpcx.perawatcher.domain.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

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
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

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
