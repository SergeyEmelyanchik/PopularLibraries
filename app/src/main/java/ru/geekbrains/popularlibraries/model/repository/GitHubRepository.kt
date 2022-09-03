package ru.geekbrains.popularlibraries.model.repository

import io.reactivex.rxjava3.core.Observable
import ru.geekbrains.popularlibraries.model.GitHubUser

interface GitHubRepository {
    fun getUsers(): Observable<List<GitHubUser>>
}