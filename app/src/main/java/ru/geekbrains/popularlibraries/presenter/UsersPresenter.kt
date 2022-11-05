package ru.geekbrains.popularlibraries.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.core.navigation.UserScreen
import ru.geekbrains.popularlibraries.core.navigation.UsersScreen
import ru.geekbrains.popularlibraries.model.repository.GitHubRepository
import ru.geekbrains.popularlibraries.utils.subscribeByDefault
import ru.geekbrains.popularlibraries.view.user.UserView
import javax.inject.Inject


class UsersPresenter() : MvpPresenter<UserView>() {

    @Inject
    lateinit var repository: GitHubRepository

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.instance.appComponent.inject(this)
        viewState.showLoading()
        Log.d("TAG", "onFirstViewAttach() called")
        repository.getUsers().subscribeByDefault()
            .subscribe({
                Log.d("TAG", "subscribe")
                Log.d("TAG", "${it.size}")
                viewState.initList(it)
                viewState.hideLoading()
            },
                {
                    viewState.errorGetUser(it.message)
                })
    }

    fun openUserScreen(userLogin: String) {
        router.navigateTo(UserScreen(userLogin))
    }

    fun onBackPressed(): Boolean {
        router.backTo(UsersScreen)
        return true
    }
}