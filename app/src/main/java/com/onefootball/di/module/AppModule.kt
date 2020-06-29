package com.onefootball.di.module

import android.content.Context
import com.onefootball.App
import com.onefootball.di.scope.AppScope
import dagger.Module

@Module
open class AppModule(private val app: App) {
    @AppScope
    fun provideContext(): Context = app
}