package ru.geekbrains.popularlibraries.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.navigation.RepoScreen
import ru.geekbrains.popularlibraries.core.navigation.UserScreen
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.model.data.ReposDto
import ru.geekbrains.popularlibraries.utils.disposebleBy
import ru.geekbrains.popularlibraries.utils.subscribeByDefault
import ru.geekbrains.popularlibraries.view.userdetails.UserDetailsView

class UserDetailsPresenter(

    private val router: Router,
    private val repository: GitHubRepository,
) : MvpPresenter<UserDetailsView>() {

    private val bag = CompositeDisposable()
    private var mLogin: String? = null
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        mLogin = login
        viewState.showLoading()
        Single.zip(
            repository.getUserByLogin(login),
            repository.getReposByLogin(login)
        ) { user, repos ->
            repos.sortedByDescending { it.createdAt }.map {
                it.createdAt = it.createdAt.substring(0, 10)
                it
            }
            Pair<GitHubUser, List<ReposDto>>(user,repos)
        }.subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.showUser(it)
        }, {
            Log.d("TAG", it.localizedMessage)
        }).disposebleBy(bag)
    }

    fun onBackPressed(): Boolean {
        mLogin?.let {
            router.navigateTo(UserScreen(it))
        }
        return true
    }

    fun openRepoScreen(repo: ReposDto) {
        router.backTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}