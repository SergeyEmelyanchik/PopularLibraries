package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.model.data.ReposDto
import ru.geekbrains.popularlibraries.view.userrepository.RepoUserView

class RepoUserPresenter(
    private val router: Router, private val repo: ReposDto?,
) : MvpPresenter<RepoUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}