package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.model.network.ReposDto
import ru.geekbrains.popularlibraries.view.userrepository.RepoUserView
import javax.inject.Inject

class RepoUserPresenter(
    private val repo: ReposDto?,
) : MvpPresenter<RepoUserView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        repo?.let { viewState.showRepo(it) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}