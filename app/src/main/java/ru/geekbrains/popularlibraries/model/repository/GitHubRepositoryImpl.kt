package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.database.UserDAO
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.data.ReposDto
import ru.geekbrains.popularlibraries.model.network.UsersApi
import ru.geekbrains.popularlibraries.utils.doCompletableIf
import ru.geekbrains.popularlibraries.utils.mapRepos
import ru.geekbrains.popularlibraries.utils.mapToDBObject
import ru.geekbrains.popularlibraries.utils.mapToEntity
import java.util.concurrent.TimeUnit


class GitHubRepositoryImpl(
    private val usersApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus: Single<Boolean>,
) : GitHubRepository {

    override fun getUsers(): Single<List<GitHubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) fetchFromApi(true)
            else getFromDb()
        }
    }

    override fun getUserByLogin(login: String): Single<GitHubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    override fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }

    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GitHubUser>> {
        return usersApi.getAllUsers().doCompletableIf(shouldPersist) {
            userDao.insertAll(it.map(::mapToDBObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getFromDb(): Single<List<GitHubUser>> {
        return userDao.queryForAllUsers().map { it.map(::mapToEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GitHubUser> {
        return userDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.userDbObject)
            user.repos = userWithRepos.repos.map { mapRepos(it) }
            user
        }
    }
}