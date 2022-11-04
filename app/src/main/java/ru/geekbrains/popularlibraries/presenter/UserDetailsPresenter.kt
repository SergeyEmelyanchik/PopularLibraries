package ru.geekbrains.popularlibraries.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.model.GitHubUserRepos
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.utils.disposebleBy
import ru.geekbrains.popularlibraries.utils.subscribeByDefault
import ru.geekbrains.popularlibraries.view.userdetails.UserDetailsView

class UserDetailsPresenter(

    private val router: Router,
    private val repository: GitHubRepository,
) : MvpPresenter<UserDetailsView>() {
    private val bag = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        viewState.showLoading()
        Single.zip(repository.getUserByLogin(login),
            repository.getReposByLogin(login)) { user, repos ->
            GitHubUserRepos(user, repos.sortedByDescending { it.createdAt })
        }.subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.showUser(it)
        }, {
            Log.d("TAG", it.localizedMessage)
        }).disposebleBy(bag)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}