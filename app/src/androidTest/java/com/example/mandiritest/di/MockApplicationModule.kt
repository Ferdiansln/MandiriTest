package com.example.mandiritest.di

import com.example.mandiritest.core.repository.NewsMockRepository
import com.example.mandiritest.core.repository.NewsRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ApplicationModule::class]
)
class MockApplicationModule {

    @Provides
    @Singleton
    fun providesNewsRepository(): NewsRemoteRepository {
        return NewsMockRepository()
    }
}