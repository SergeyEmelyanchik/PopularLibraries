package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.core.navigation.UserScreen
import ru.geekbrains.popularlibraries.core.navigation.UsersScreen
import ru.geekbrains.popularlibraries.model.repository.screen.UsersRepoScreen
import ru.geekbrains.popularlibraries.utils.subscribeByDefault
import ru.geekbrains.popularlibraries.view.user.UserView
import javax.inject.Inject


class UsersPresenter() : MvpPresenter<UserView>() {

    @Inject
    lateinit var repository: UsersRepoScreen

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        App.instance.appComponent.inject(this)
        repository.getUsers().subscribeByDefault()
            .subscribe({
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