package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.database.dao.UserRepoDao
import ru.geekbrains.popularlibraries.model.database.dao.UsersDao
import ru.geekbrains.popularlibraries.model.network.ReposDto
import ru.geekbrains.popularlibraries.model.network.GitHubApi
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.utils.*
import java.util.concurrent.TimeUnit


class GitHubRepositoryImpl(
    private val githubApiRepo: GitHubApiRepo,
    private val usersDao: UsersDao,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable,
    private val userRepoDao: UserRepoDao,
) : GitHubRepository {

    override fun getUsers(): Single<List<GitHubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) getUsersApi(true)
            else getUsersBD()
        }
    }

    private fun getUsersApi(shouldPersist: Boolean): Single<List<GitHubUser>> {
        return githubApiRepo.getAllUsers().doCompletableIf(shouldPersist) {
            roomCache.insertUserList(it.map(::mapToDBObject))
        }.map { it.map(::mapToEntity) }
    }

    private fun getUsersBD(): Single<List<GitHubUser>> {
        return usersDao.queryForAllUsers().map { it.map(::mapToEntity) }
    }

    override fun getUserWithReposByLogin(login: String): Single<GitHubUser> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                getUserWithRepoApi(login)
            } else {
                getUserWithReposBD(login)
            }
        }
    }


    private fun getUserWithReposBD(login: String): Single<GitHubUser> {
        return userRepoDao.getUsersWithRepos(login).map { userWithRepos ->
            val user = mapToEntity(userWithRepos.usersDbEntity)
            user.repos = userWithRepos.repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                mapRepos(it)
            }
            user
        }
    }

    private fun getUserWithRepoApi(login: String): Single<GitHubUser> {
        return Single.zip(
            getUserByLogin(login),
            getReposByLogin(login)
        ) { user, repos ->
            repos.map {
                it.createdAt = it.createdAt?.substring(0, 10)
                it
            }
            user.repos = repos
            user
        }.doCompletableIf(true) { user ->
            user.repos?.let { repos ->
                roomCache.insertRepoList(repos.map { repo ->
                    mapReposToObject(repo, user.id)
                })
            } ?: Completable.create {
                it.onError(Throwable(message = "Repos is Empty"))
            }
        }
    }

    private fun getUserByLogin(login: String): Single<GitHubUser> {
        return githubApiRepo.getUser(login).map(::mapToEntity).delay(500, TimeUnit.MILLISECONDS)
    }

    private fun getReposByLogin(login: String): Single<List<ReposDto>> {
        return githubApiRepo.getRepos(login)
    }
}