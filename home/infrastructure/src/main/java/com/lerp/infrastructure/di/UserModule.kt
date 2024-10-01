package com.lerp.infrastructure.di

import com.lerp.domain.repository.UserRepository
import com.lerp.domain.usecase.GetListUserUseCase
import com.lerp.infrastructure.repository.UserService
import com.lerp.infrastructure.repository.remote.UserListRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideGetUserList(apiService: UserService): UserRepository =
        UserListRemote(apiService)

    @Provides
    @Singleton
    fun provideGetListUserUseCase(repository: UserRepository): GetListUserUseCase =
        GetListUserUseCase(repository)

}