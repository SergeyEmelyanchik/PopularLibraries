package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.geekbrains.popularlibraries.model.database.RepoDBObject
import ru.geekbrains.popularlibraries.model.database.UserDAO
import ru.geekbrains.popularlibraries.model.database.UserDBObject

class RoomCache(private val userDao: UserDAO) : Cacheable {
    override fun insertRepoList(list: List<RepoDBObject>): Completable {
        return userDao.insertAllRepos(list)
    }

    override fun insertUserList(list: List<UserDBObject>): Completable {
        return userDao.insertAll(list)
    }
}