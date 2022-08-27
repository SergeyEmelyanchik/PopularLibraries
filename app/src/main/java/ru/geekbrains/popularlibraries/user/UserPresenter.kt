package ru.geekbrains.popularlibraries.user

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.repository.GitHubRepository

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
}