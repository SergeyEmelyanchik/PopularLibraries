package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

interface Cacheable {
    fun insertRepoList(list: List<UserRepoDBEntity>): Completable
    fun insertUserList(list: List<UsersDBEntity>): Completable
}