package com.creativegrpcx.perawatcher.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.creativegrpcx.perawatcher.repository.dao.WalletDao
import com.creativegrpcx.perawatcher.repository.dao.TransactionDao
import com.creativegrpcx.perawatcher.repository.entities.Wallet
import com.creativegrpcx.perawatcher.repository.entities.Transaction
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Transaction::class , Wallet::class] , version = 1 , exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun transactionDao() : TransactionDao
    abstract fun walletDao() : WalletDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "pera_watcher"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}

