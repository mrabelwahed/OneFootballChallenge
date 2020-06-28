package com.onefootball

import com.onefootball.di.component.AppComponent
import com.onefootball.di.component.DaggerAppComponent
import com.onefootball.di.module.TestAppModule

class TestApp : App() {
    override fun getApplicationComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(TestAppModule(this))
            .build()
    }
}