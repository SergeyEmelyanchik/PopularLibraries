package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.geekbrains.popularlibraries.model.database.dao.UserRepoDao
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

class RoomCache(private val usersDao: UsersDao, private val userRepoDao: UserRepoDao) : Cacheable {
    override fun insertRepoList(list: List<UserRepoDBEntity>): Completable {
        return userRepoDao.insertAllRepos(list)
    }

    override fun insertUserList(list: List<UsersDBEntity>): Completable {
        return usersDao.insertAll(list)
    }
}