package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.data.ReposDto

interface GitHubRepository {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserByLogin(login: String): Single<GitHubUser>
    fun getReposByLogin(login: String): Single<List<ReposDto>>
    fun getUserWithRepos(login: String): Single<GitHubUser>
}