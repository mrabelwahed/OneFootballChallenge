package com.onefootball

import android.app.Application
import com.onefootball.di.component.AppComponent
import com.onefootball.di.component.DaggerAppComponent
import com.onefootball.di.module.AppModule

open class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = getApplicationComponent()
        appComponent.inject(this)
    }

    open fun getApplicationComponent(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
}