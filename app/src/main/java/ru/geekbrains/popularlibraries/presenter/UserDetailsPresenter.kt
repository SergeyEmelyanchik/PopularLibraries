package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.navigation.UserScreen
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.view.users.UserView
import ru.geekbrains.popularlibraries.view.users.user.UserDetailsView

class UserDetailsPresenter(
    private val router: Router,
    private val user: GitHubUser?,
) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUser(user?.login)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}