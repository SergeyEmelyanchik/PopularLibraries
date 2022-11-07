package ru.geekbrains.popularlibraries.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.model.database.GitHubDB
import ru.geekbrains.popularlibraries.model.database.dao.UserRepoDao
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun database(context: Context): GitHubDB =
        GitHubDB.create(context)

    @Singleton
    @Provides
    fun usersDao(database: GitHubDB): UsersDao =
        database.usersDao()

    @Singleton
    @Provides
    fun userRepoDao(database: GitHubDB): UserRepoDao =
        database.userRepoDao()
}