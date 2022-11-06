package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.model.database.UserDAO
import ru.geekbrains.popularlibraries.model.repository.Cacheable
import ru.geekbrains.popularlibraries.model.repository.RoomCache
import javax.inject.Singleton

@Module
object CacheModule {
    @Singleton
    @Provides
    fun cacheable(userDAO: UserDAO): Cacheable {
        return RoomCache(userDAO)
    }
}