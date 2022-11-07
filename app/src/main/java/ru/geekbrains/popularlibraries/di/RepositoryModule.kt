package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.core.ConnectivityListener
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.network.UsersApi
import ru.geekbrains.popularlibraries.model.repository.Cacheable
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.model.repository.GitHubRepositoryImpl
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        usersApi: UsersApi,
        usersDao: UsersDao,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): GitHubRepository {
        return GitHubRepositoryImpl(usersApi, usersDao, networkStatus.statusSingle(), cacheable)
    }
}