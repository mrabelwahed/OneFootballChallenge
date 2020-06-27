package com.onefootball.di.component

import com.onefootball.App
import com.onefootball.di.module.AppModule
import com.onefootball.di.module.GetNewsListModule
import com.onefootball.di.module.NewsActivityModule
import com.onefootball.di.module.NewsRepositoryModule
import com.onefootball.di.scope.AppScope
import com.onefootball.ui.features.News.view.MyNewsActivity
import dagger.Component
@AppScope
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(target: App)
}