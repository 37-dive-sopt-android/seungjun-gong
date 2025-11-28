package com.sopt.dive.data.remote.di

import com.sopt.dive.data.remote.datasource.auth.AuthDataSource
import com.sopt.dive.data.remote.datasource.profile.ProfileDataSource
import com.sopt.dive.data.remote.datasourceimpl.auth.AuthDataSourceImpl
import com.sopt.dive.data.remote.datasourceimpl.profile.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl,
    ): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindProfileDataSource(
        profileDataSourceImpl: ProfileDataSourceImpl,
    ): ProfileDataSource
}
