package com.alanturing.cpifp.login.di

import com.alanturing.cpifp.login.network.AuthorizationInteceptor
import com.alanturing.cpifp.login.network.CsrfInterceptor
import com.alanturing.cpifp.login.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserServiceModule {

    @Provides
    @Singleton
    fun provideHttpClient( csrfInterceptor: CsrfInterceptor,
                           authInterceptor:AuthorizationInteceptor): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(csrfInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    }
    @Provides
    @Singleton
    fun provideUserService(
        client:OkHttpClient
    ): UserService {
        val baseUrl = "https://83d9-79-147-188-107.ngrok-free.app"
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }
}
