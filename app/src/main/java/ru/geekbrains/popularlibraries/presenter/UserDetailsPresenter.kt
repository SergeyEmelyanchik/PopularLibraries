package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
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
        repository.getUsersById(login).subscribeByDefault()
            .subscribe({ user ->
                viewState.showUser(user)
                viewState.hideLoading()
            },
                {
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