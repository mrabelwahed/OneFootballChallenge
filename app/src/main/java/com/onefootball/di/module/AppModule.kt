package com.onefootball.di.module

import android.app.Application
import android.content.Context
import com.onefootball.App
import com.onefootball.di.scope.AppScope
import dagger.Module
import javax.inject.Singleton

@Module
class AppModule (private val app: App) {
    @AppScope
    fun provideContext(): Context = app
}