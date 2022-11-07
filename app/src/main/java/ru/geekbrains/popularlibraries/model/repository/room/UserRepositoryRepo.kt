package ru.geekbrains.popularlibraries.model.repository.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.database.UserWithReposDBObject
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity

interface UserRepositoryRepo {
    fun insertAllRepos(userRepoDbEntity: List<UserRepoDBEntity>): Completable
    fun queryForAllRepos(): Single<List<UserRepoDBEntity>>
    fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>
}