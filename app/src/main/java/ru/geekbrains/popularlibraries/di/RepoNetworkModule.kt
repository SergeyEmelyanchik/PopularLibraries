package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibraries.model.network.GitHubApi
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepoImpl
import javax.inject.Singleton

@Module
object RepoNetworkModule {
    @Provides
    @Singleton
    fun provideRepoNetwork(githubApi: GitHubApi): GitHubApiRepo {
        return GitHubApiRepoImpl(githubApi)
    }
}