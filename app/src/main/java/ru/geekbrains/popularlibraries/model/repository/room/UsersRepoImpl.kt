package ru.geekbrains.popularlibraries.model.repository.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

class UsersRepoImpl(private val usersDao: UsersDao) : UsersRepo {
    override fun insertAll(usersDbEntity: List<UsersDBEntity>): Completable {
        return usersDao.insertAll(usersDbEntity)
    }

    override fun queryForAllUsers(): Single<List<UsersDBEntity>> {
        return usersDao.queryForAllUsers()
    }
}