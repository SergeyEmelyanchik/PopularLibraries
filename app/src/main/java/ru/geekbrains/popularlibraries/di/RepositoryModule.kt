package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.core.ConnectivityListener
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.model.repository.room.Cacheable
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.model.repository.GitHubRepositoryImpl
import ru.geekbrains.popularlibraries.model.repository.room.UserRepositoryRepo
import ru.geekbrains.popularlibraries.model.repository.room.UsersRepo
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        githubApiRepo: GitHubApiRepo,
        usersRepo: UsersRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): GitHubRepository {
        return GitHubRepositoryImpl(
            githubApiRepo,
            usersRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}