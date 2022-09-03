package ru.geekbrains.popularlibraries.view.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.popularlibraries.model.GitHubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {

    fun initList(list: List<GitHubUser>)
    fun showLoading()
    fun hideLoading()
    fun errorGetUser(message: String?)
}

interface ItemClickListener {
    fun onUserClick(user: GitHubUser)
}