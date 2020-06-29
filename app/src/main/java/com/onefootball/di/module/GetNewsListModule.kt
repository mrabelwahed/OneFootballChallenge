package com.onefootball.di.module

import com.ramadan.data.repository.NewsRepositoryImpl
import com.ramadan.domain.interactor.GetNewsList
import dagger.Module
import dagger.Provides

@Module
class GetNewsListModule {
    @Provides
    fun provideGetNewsList(repo: NewsRepositoryImpl) = GetNewsList(repo)
}