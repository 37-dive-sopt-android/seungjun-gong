package com.sopt.dive.data.di

import com.sopt.dive.data.repository.auth.AuthRepository
import com.sopt.dive.data.repository.profile.ProfileRepository
import com.sopt.dive.data.repositoryImpl.auth.AuthRepositoryImpl
import com.sopt.dive.data.repositoryImpl.profile.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl,
    ): ProfileRepository
}
