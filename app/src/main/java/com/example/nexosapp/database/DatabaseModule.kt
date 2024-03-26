package com.example.nexosapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCompanyDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            Database::class.java,
            "CompanyDB",
        ).build()

    @Provides
    @Singleton
    fun provideAuthorizationDAO(companyDatabase: Database) =
        companyDatabase.authorizationDao()

    @Provides
    @Singleton
    fun provideDatabaseRepository(authorizationDAO: AuthorizationDAO): DatabaseRepository =
        DatabaseRepositoryImpl(authorizationDAO)

}