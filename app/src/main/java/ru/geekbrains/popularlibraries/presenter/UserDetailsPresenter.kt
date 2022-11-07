package ru.geekbrains.popularlibraries.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.core.navigation.RepoScreen
import ru.geekbrains.popularlibraries.core.navigation.UserScreen
import ru.geekbrains.popularlibraries.di.RepositorySubcomponent
import ru.geekbrains.popularlibraries.model.network.ReposDto
import ru.geekbrains.popularlibraries.model.repository.screen.UserDetailsRepoScreen
import ru.geekbrains.popularlibraries.utils.disposebleBy
import ru.geekbrains.popularlibraries.utils.subscribeByDefault
import ru.geekbrains.popularlibraries.view.userdetails.UserDetailsView
import javax.inject.Inject

class UserDetailsPresenter(private val string: String?) : MvpPresenter<UserDetailsView>() {
    var userSubcomponent: RepositorySubcomponent? = null

    @Inject
    lateinit var repository: UserDetailsRepoScreen

    @Inject
    lateinit var router: Router

    private val bag = CompositeDisposable()
    private var mLogin: String? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        userSubcomponent = App.instance.appComponent.userSubcomponent()
        userSubcomponent?.inject(this)
        loadUser(string!!)
    }


    fun loadUser(login: String) {
        mLogin = login
        viewState.showLoading()
        repository.getUserWithReposByLogin(login).subscribeByDefault().subscribe({
            viewState.hideLoading()
            viewState.showUser(it)
        }, {
            Log.d("TAG", it.localizedMessage)
        }).disposebleBy(bag)
    }

    fun onBackPressed(): Boolean {
        mLogin?.let {
            router.backTo(UserScreen(it))
        }
        return true
    }

    fun openRepoScreen(repo: ReposDto) {
        router.navigateTo(RepoScreen(repo))
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
        userSubcomponent = null
    }
}