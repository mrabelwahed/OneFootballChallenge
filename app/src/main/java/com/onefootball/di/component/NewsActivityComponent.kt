package com.onefootball.di.component

import com.onefootball.di.module.*
import com.onefootball.di.scope.ActivityScope
import com.onefootball.ui.features.News.view.MyNewsActivity
import dagger.Component

@ActivityScope
@Component(
    modules = [
        NewsActivityModule::class,
        NewsRepositoryModule::class,
        GetNewsListModule::class,
        NewsVMModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [AppComponent::class]
)
interface NewsActivityComponent {
    fun inject(target: MyNewsActivity)
}