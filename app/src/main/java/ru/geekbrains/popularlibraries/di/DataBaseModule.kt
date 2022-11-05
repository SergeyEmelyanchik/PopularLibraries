package ru.geekbrains.popularlibraries.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.model.database.GitHubDB
import ru.geekbrains.popularlibraries.model.database.UserDAO
import javax.inject.Singleton

@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun database(context: Context): GitHubDB =
        GitHubDB.create(context)

    @Singleton
    @Provides
    fun userDao(database: GitHubDB): UserDAO =
        database.userDao()
}