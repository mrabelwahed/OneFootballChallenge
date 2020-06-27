package com.onefootball.di.module

import androidx.lifecycle.ViewModel
import com.onefootball.di.vmfactory.ViewModelKey
import com.onefootball.ui.features.News.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsVMModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}