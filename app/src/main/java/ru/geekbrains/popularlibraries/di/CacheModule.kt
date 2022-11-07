package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.repository.Cacheable
import ru.geekbrains.popularlibraries.model.repository.RoomCache
import javax.inject.Singleton

@Module
object CacheModule {
    @Singleton
    @Provides
    fun cacheable(usersDao: UsersDao): Cacheable {
        return RoomCache(usersDao)
    }
}