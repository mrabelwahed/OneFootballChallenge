package com.onefootball.di.module

import com.onefootball.App
import com.ramadan.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NewsRepositoryModule(private val app: App) {
    @Provides
    fun provideNewsRepository() = NewsRepositoryImpl(app)
}