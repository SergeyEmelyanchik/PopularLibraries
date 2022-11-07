package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.core.ConnectivityListener
import ru.geekbrains.popularlibraries.model.database.dao.UserRepoDao
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.model.repository.Cacheable
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.model.repository.GitHubRepositoryImpl
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        githubApiRepo: GitHubApiRepo,
        usersDao: UsersDao,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
        userRepoDao: UserRepoDao,
    ): GitHubRepository {
        return GitHubRepositoryImpl(
            githubApiRepo,
            usersDao,
            networkStatus.statusSingle(),
            cacheable,
            userRepoDao
        )
    }
}