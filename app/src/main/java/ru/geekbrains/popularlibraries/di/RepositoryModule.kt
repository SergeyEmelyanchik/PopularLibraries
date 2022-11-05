package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.core.ConnectivityListener
import ru.geekbrains.popularlibraries.model.database.UserDAO
import ru.geekbrains.popularlibraries.model.network.UsersApi
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.model.repository.GitHubRepositoryImpl
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        usersApi: UsersApi,
        userDao: UserDAO,
        networkStatus: ConnectivityListener,
    ): GitHubRepository {
        return GitHubRepositoryImpl(usersApi, userDao, networkStatus.statusSingle())
    }
}