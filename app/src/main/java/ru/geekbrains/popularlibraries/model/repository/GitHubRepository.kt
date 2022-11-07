package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser

interface GitHubRepository {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserWithReposByLogin(login: String): Single<GitHubUser>
}