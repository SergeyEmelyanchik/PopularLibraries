package ru.geekbrains.popularlibraries.model.repository.implementation

import io.reactivex.rxjava3.core.Observable
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import java.util.concurrent.TimeUnit

class GitHubRepositoryImpl : GitHubRepository {

    private val repositories = listOf(
        GitHubUser("Arnold Schwarzenegger"),
        GitHubUser("Sylvester Stallone"),
        GitHubUser("Jan-Clod Van-Damme"),
        GitHubUser("Bruce Willis"),
        GitHubUser("Gerard Butler"),
        GitHubUser("Sandra Bullock")
    )

    override fun getUsers(): Observable<List<GitHubUser>> {
        return Observable.fromIterable(listOf(repositories)).delay(1, TimeUnit.SECONDS)
    }

}