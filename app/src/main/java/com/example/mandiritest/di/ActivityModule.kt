package com.example.mandiritest.di

import com.example.mandiritest.core.usecase.NewsUseCase
import com.example.mandiritest.ui.article.ArticleListViewModel
import com.example.mandiritest.ui.main.MainViewModel
import com.example.mandiritest.ui.source.SourceListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideMainViewModel(): MainViewModel = MainViewModel()

    @Provides
    fun provideSourceListViewModel(newsUseCase: NewsUseCase): SourceListViewModel =
        SourceListViewModel(newsUseCase)

    @Provides
    fun provideArticleListViewModel(newsUseCase: NewsUseCase): ArticleListViewModel =
        ArticleListViewModel(newsUseCase)
}
