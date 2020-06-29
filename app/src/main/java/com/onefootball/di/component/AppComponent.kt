package com.onefootball.di.component

import com.onefootball.App
import com.onefootball.di.module.AppModule
import com.onefootball.di.scope.AppScope
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