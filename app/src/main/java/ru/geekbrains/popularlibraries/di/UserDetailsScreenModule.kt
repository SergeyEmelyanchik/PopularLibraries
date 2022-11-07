package ru.geekbrains.popularlibraries.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import ru.geekbrains.popularlibraries.core.ConnectivityListener
import ru.geekbrains.popularlibraries.model.network.GitHubApiRepo
import ru.geekbrains.popularlibraries.model.repository.room.Cacheable
import ru.geekbrains.popularlibraries.model.repository.room.UserRepositoryRepo
import ru.geekbrains.popularlibraries.model.repository.screen.UserDetailsRepoScreen
import ru.geekbrains.popularlibraries.model.repository.screen.UserDetailsRepoScreenImpl
import ru.geekbrains.popularlibraries.presenter.UserDetailsPresenter
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryScope

@Module
open class UserDetailsScreenModule {

    @Provides
    @RepositoryScope
    fun provideUserDetailsRepository(
        githubApiRepo: GitHubApiRepo,
        userRepositoryRepo: UserRepositoryRepo,
        networkStatus: ConnectivityListener,
        cacheable: Cacheable,
    ): UserDetailsRepoScreen {
        return UserDetailsRepoScreenImpl(
            githubApiRepo,
            userRepositoryRepo,
            networkStatus.statusSingle(),
            cacheable,
        )
    }
}

@RepositoryScope
@Subcomponent(
    modules = [
        UserDetailsScreenModule::class
    ]
)

interface RepositorySubcomponent {
    fun inject(userPresenter: UserDetailsPresenter)
}
