package ru.geekbrains.popularlibraries.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.model.database.GitHubDB
import ru.geekbrains.popularlibraries.model.repository.room.*
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun database(context: Context): GitHubDB =
        GitHubDB.create(context)

    @Singleton
    @Provides
    fun usersDao(database: GitHubDB): UsersRepo =
        UsersRepoImpl(database.usersDao())

    @Singleton
    @Provides
    fun userRepoDao(database: GitHubDB): UserRepositoryRepo =
        UserRepositoryRepoImpl(database.userRepoDao())

    @Singleton
    @Provides
    fun cacheable(usersRepo: UsersRepo, userRepositoryRepo: UserRepositoryRepo): Cacheable {
        return CacheableImpl(usersRepo, userRepositoryRepo)
    }
}