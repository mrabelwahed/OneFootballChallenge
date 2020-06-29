package com.onefootball.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.App
import com.onefootball.di.scope.ActivityScope
import com.onefootball.ui.features.News.view.NewsAdapter
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule(private val app: App) {
    @ActivityScope
    @Provides
    fun provideNewsAdapter(): NewsAdapter = NewsAdapter()

    @ActivityScope
    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(app)
}