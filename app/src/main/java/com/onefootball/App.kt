package com.onefootball

import android.app.Application
import com.onefootball.di.component.AppComponent
import com.onefootball.di.component.DaggerAppComponent
import com.onefootball.di.module.AppModule
import com.onefootball.di.module.GetNewsListModule
import com.onefootball.di.module.NewsActivityModule
import com.onefootball.di.module.NewsRepositoryModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = getApplicationComponent()
        appComponent.inject(this)
    }

     fun getApplicationComponent(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
}