package ru.geekbrains.popularlibraries.view.userdetails

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.data.ReposDto

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun showUser(user: Pair<GitHubUser, List<ReposDto>>)
    fun showLoading()
    fun hideLoading()
}