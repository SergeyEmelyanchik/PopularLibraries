package ru.geekbrains.popularlibraries.repository

import ru.geekbrains.popularlibraries.model.GitHubUser

interface GitHubRepository {
    fun getUsers():List<GitHubUser>
}