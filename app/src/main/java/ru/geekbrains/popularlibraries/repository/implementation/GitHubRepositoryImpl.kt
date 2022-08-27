package ru.geekbrains.popularlibraries.repository.implementation

import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.repository.GitHubRepository

class GitHubRepositoryImpl : GitHubRepository {

    private val repositories = listOf(
        GitHubUser("Arnold Schwarzenegger"),
        GitHubUser("Sylvester Stallone"),
        GitHubUser("Jan-Clod Van-Damme"),
        GitHubUser("Bruce Willis"),
        GitHubUser("Gerard Butler"),
        GitHubUser("Sandra Bullock")
    )

    override fun getUsers(): List<GitHubUser> {
        return repositories
    }

}