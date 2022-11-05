package ru.geekbrains.popularlibraries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(UserDbObject::class, RepoDBObject::class),
    version = 1,
    exportSchema = false
)

abstract class GitHubDB : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        fun create(context: Context): GitHubDB {
            return Room.databaseBuilder(context, GitHubDB::class.java, "github.db").build()
        }
    }
}