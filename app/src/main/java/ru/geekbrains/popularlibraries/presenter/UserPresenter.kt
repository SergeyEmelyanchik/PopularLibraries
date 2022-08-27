package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.navigation.UserDetailsScreen
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.view.users.UserView

class UserPresenter(
    private val repository: GitHubRepository,
    private val router: Router
) :
    MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
    fun showDetails(githubUser: GitHubUser?){
        val login = githubUser ?: GitHubUser("empty")
        router.navigateTo(UserDetailsScreen(login))
    }
}