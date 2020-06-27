package com.onefootball.di.module

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.App
import com.onefootball.di.scope.ActivityScope
import com.onefootball.di.scope.AppScope
import com.onefootball.ui.features.News.view.NewsAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsActivityModule (private val app: App) {
    @ActivityScope
    @Provides
    fun provideNewsAdapter(): NewsAdapter = NewsAdapter()

    @ActivityScope
    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(app)
}