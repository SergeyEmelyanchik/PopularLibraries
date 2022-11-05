package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Completable
import ru.geekbrains.popularlibraries.model.database.RepoDBObject
import ru.geekbrains.popularlibraries.model.database.UserDBObject

interface Cacheable {
    fun insertRepoList(list: List<RepoDBObject>): Completable
    fun insertUserList(list: List<UserDBObject>): Completable
}