package ru.geekbrains.popularlibraries.model.repository.screen

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.GitHubUser

interface UserDetailsRepoScreen {
    fun getUserWithReposByLogin(login: String): Single<GitHubUser>
}