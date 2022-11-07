package ru.geekbrains.popularlibraries.di

import dagger.Component
import ru.geekbrains.popularlibraries.presenter.MainPresenter
import ru.geekbrains.popularlibraries.presenter.RepoUserPresenter
import ru.geekbrains.popularlibraries.presenter.UserDetailsPresenter
import ru.geekbrains.popularlibraries.presenter.UsersPresenter
import ru.geekbrains.popularlibraries.view.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        DataBaseModule::class,
        NavigationModule::class,
        RepositoryModule::class,
        RepoNetworkModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(detailsPresenter: UserDetailsPresenter)
    fun inject(repoUserPresenter: RepoUserPresenter)
}