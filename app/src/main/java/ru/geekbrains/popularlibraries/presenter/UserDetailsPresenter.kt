package ru.geekbrains.popularlibraries.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.view.users.user.UserDetailsView

class UserDetailsPresenter(private val router: Router) : MvpPresenter<UserDetailsView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}