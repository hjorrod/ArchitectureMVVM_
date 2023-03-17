package com.inditex.architecturemvvm.di

import android.app.Application
import androidx.room.Room
import com.inditex.architecturemvvm.BuildConfig
import com.inditex.architecturemvvm.data.api.example.ExampleApi
import com.inditex.architecturemvvm.data.dao.AppDatabase
import com.inditex.architecturemvvm.data.datasource.local.LocalDS
import com.inditex.architecturemvvm.data.datasource.local.impl.LocalDSImpl
import com.inditex.architecturemvvm.data.datasource.remote.RemoteDS
import com.inditex.architecturemvvm.data.datasource.remote.impl.RemoteDSImpl
import com.inditex.architecturemvvm.data.repository.ExampleApiRepository
import com.inditex.architecturemvvm.data.repository.impl.ExampleApiRepositoryImpl
import com.inditex.architecturemvvm.data.util.CustomInterceptor
import com.inditex.architecturemvvm.domain.usecase.GetFirstDataExampleUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "example_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDaoModule(
        appDatabase: AppDatabase
    ) = LocalDSImpl(appDatabase.exampleDao())

    @Singleton
    @Provides
    fun provideHttpInterceptorOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .addInterceptor(CustomInterceptor())
            .retryOnConnectionFailure(true).build()
    }

    @Singleton
    @Provides
    fun provideExampleApi(client: OkHttpClient = provideHttpInterceptorOkHttpClient()): ExampleApi =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ExampleApi::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBindings {
        @Binds
        fun bindExampleRepositoryModule(impl: ExampleApiRepositoryImpl): ExampleApiRepository

        @Binds
        fun bindLocalModule(impl: LocalDSImpl): LocalDS

        @Binds
        fun bindRemoteModule(impl: RemoteDSImpl): RemoteDS
    }
}