package com.coderops.melodymeter.di

import com.coderops.repository.AuthRepository
import com.coderops.repository.MovieRepository
import com.coderops.repository.MovieRepositoryImpl
import com.coderops.repository.TriviaRepository
import com.coderops.repository.TriviaRepositoryIml
import com.coderops.repository.WatchHistoryRepositoryImpl
import com.coderops.repository.auth.AuthRepositoryImpl
import com.coderops.usecase.watch_history.WatchHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun bindTriviaRepository(triviaRepositoryImpl: TriviaRepositoryIml): TriviaRepository

    @Binds
    @ViewModelScoped abstract fun bindWatchHistoryRepository(watchHistoryRepositoryImpl: WatchHistoryRepositoryImpl): WatchHistoryRepository

}