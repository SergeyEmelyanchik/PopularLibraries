package ru.geekbrains.popularlibraries.model.repository

import ru.geekbrains.popularlibraries.model.GitHubUser

interface GitHubRepository {
    fun getUsers():List<GitHubUser>
}