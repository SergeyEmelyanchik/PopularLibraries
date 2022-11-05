package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.data.ReposDto

interface GitHubRepository {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserWithReposByLogin(login: String): Single<Pair<GitHubUser, List<ReposDto>>>
    fun checkStatusNetwork(): Single<Boolean>
}