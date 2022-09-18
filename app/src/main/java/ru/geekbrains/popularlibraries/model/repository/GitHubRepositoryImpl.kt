package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.network.UsersApi
import ru.geekbrains.popularlibraries.utils.mapToEntity
import java.util.concurrent.TimeUnit


class GitHubRepositoryImpl(private val usersApi: UsersApi) : GitHubRepository {
    override fun getUsers(): Single<List<GitHubUser>> {
        return usersApi.getAllUsers().map { it.map(::mapToEntity) }.delay(1, TimeUnit.SECONDS)
    }

    override fun getUsersById(login: String): Single<GitHubUser> {
        return usersApi.getUser(login).map(::mapToEntity).delay(1, TimeUnit.SECONDS)
    }

}