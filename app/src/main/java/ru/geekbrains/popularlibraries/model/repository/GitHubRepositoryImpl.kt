package ru.geekbrains.popularlibraries.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.database.UserDAO
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
            if (hasConnection) fetchFromApi(true)
            else getFromDb()
        }
    }

    override fun getUserWithReposByLogin(login: String): Single<Pair<GitHubUser, List<ReposDto>>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getZip(login)
            } else {
                getUserWithRepos(login)
            }
        }
    }


    fun getZip(login: String) =
        Single.zip(
            getUserByLogin(login),
            getReposByLogin(login)
        ) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            Log.d("TAG", "getZip() called with: user = $user, repos = $repos")
            Pair<GitHubUser, List<ReposDto>>(user,
                repos.sortedByDescending { it.createdAt })
        }.doCompletableIf(true) { pair ->
            userDao.insertAllRepos(pair.second.map {
                mapReposToObject(it, pair.first.id)
            })
        }

    fun getUserByLogin(login: String): Single<GitHubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    fun getReposByLogin(login: String): Single<List<ReposDto>> {
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

    override fun getUserWithRepos(login: String): Single<Pair<GitHubUser, List<ReposDto>>> {
        return userDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.userDbObject)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            Pair(user, user.repos!!.sortedByDescending { it.createdAt })
        }
    }
}