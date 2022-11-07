package ru.geekbrains.popularlibraries.model.repository.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

interface UsersRepo {
    fun insertAll(usersDbEntity: List<UsersDBEntity>): Completable
    fun queryForAllUsers(): Single<List<UsersDBEntity>>
}