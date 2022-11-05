package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.database.UserDAO
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.data.ReposDto
import ru.geekbrains.popularlibraries.model.network.UsersApi
import ru.geekbrains.popularlibraries.utils.*
import java.util.concurrent.TimeUnit


class GitHubRepositoryImpl(
    private val usersApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus: Single<Boolean>,
) : GitHubRepository {

    override fun getUsers(): Single<List<GitHubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) getUsersApi(true)
            else getUsersBD()
        }
    }

    override fun getUserWithReposByLogin(login: String): Single<Pair<GitHubUser, List<ReposDto>>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getUserWithRepoApi(login)
            } else {
                getUserWithReposBD(login)
            }
        }
    }


    override fun checkStatusNetwork(): Single<Boolean> {
        return networkStatus
    }

    private fun getUsersApi(shouldPersist: Boolean): Single<List<GitHubUser>> {
        return usersApi.getAllUsers().doCompletableIf(shouldPersist) {
            userDao.insertAll(it.map(::mapToDBObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getUsersBD(): Single<List<GitHubUser>> {
        return userDao.queryForAllUsers().map { it.map(::mapToEntity) }
    }

    private fun getUserWithReposBD(login: String): Single<Pair<GitHubUser, List<ReposDto>>> {
        return userDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.userDBObject)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            Pair(user, user.repos!!.sortedByDescending { it.createdAt })
        }
    }

    private fun getUserWithRepoApi(login: String): Single<Pair<GitHubUser, List<ReposDto>>> {
        return Single.zip(
            getUserByLogin(login),
            getReposByLogin(login)
        ) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            Pair<GitHubUser, List<ReposDto>>(user,
                repos.sortedByDescending { it.createdAt })
        }.doCompletableIf(true) { pair ->
            userDao.insertAllRepos(pair.second.map {
                mapReposToObject(it, pair.first.id)
            })
        }
    }

    private fun getUserByLogin(login: String): Single<GitHubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    private fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return usersApi.getRepos(login)
    }
}