package com.onefootball.di.module

import com.onefootball.di.scope.AppScope
import com.ramadan.data.repository.NewsRepositoryImpl
import com.ramadan.domain.interactor.GetNewsList
import com.ramadan.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GetNewsListModule{
    @Provides
    fun provideGetNewsList(repo: NewsRepositoryImpl) = GetNewsList(repo)
}