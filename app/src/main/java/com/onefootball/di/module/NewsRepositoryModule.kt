package com.onefootball.di.module

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.App
import com.onefootball.di.scope.AppScope
import com.ramadan.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsRepositoryModule(private val  app :App ) {
    @Provides
    fun provideNewsRepository() = NewsRepositoryImpl(app)
}