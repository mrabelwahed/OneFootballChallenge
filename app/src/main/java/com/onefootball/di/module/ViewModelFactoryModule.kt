package com.onefootball.di.module


import androidx.lifecycle.ViewModelProvider
import com.onefootball.di.vmfactory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}