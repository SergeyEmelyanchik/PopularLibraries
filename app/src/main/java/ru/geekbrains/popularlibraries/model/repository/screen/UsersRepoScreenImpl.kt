package ru.geekbrains.popularlibraries.model.repository.screen

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.model.repository.room.Cacheable
import ru.geekbrains.popularlibraries.model.repository.room.UsersRepo
import ru.geekbrains.popularlibraries.utils.doCompletableIf
import ru.geekbrains.popularlibraries.utils.mapToDBObject
import ru.geekbrains.popularlibraries.utils.mapToEntity

class UsersRepoScreenImpl(
    private val githubApiRepo: GitHubApiRepo,
    private val usersRepo: UsersRepo,
    private val networkStatus: Single<Boolean>,
    private val roomCache: Cacheable,
) : UsersRepoScreen {
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
        return usersRepo.queryForAllUsers().map { it.map(::mapToEntity) }
    }
}