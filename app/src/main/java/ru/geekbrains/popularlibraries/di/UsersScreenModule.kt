package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.core.ConnectivityListener
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.model.repository.room.Cacheable
import ru.geekbrains.popularlibraries.model.repository.room.UsersRepo
import ru.geekbrains.popularlibraries.model.repository.screen.UsersRepoScreen
import ru.geekbrains.popularlibraries.model.repository.screen.UsersRepoScreenImpl
import javax.inject.Singleton

@Module()
object UsersScreenModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        githubApiRepo: GitHubApiRepo,
        usersRepo: UsersRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): UsersRepoScreen {
        return UsersRepoScreenImpl(
            githubApiRepo,
            usersRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}