package ru.geekbrains.popularlibraries.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrains.popularlibraries.model.database.dao.UserRepoDao
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

@Database(
    entities = [UsersDBEntity::class, UserRepoDBEntity::class],
    version = 1,
    exportSchema = false
)

abstract class GitHubDB : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun userRepoDao(): UserRepoDao

    companion object {
        fun create(context: Context): GitHubDB {
            return Room.databaseBuilder(context, GitHubDB::class.java, "github.db").build()
        }
    }
}