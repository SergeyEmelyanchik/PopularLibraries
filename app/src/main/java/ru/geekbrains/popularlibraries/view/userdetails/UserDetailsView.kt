package ru.geekbrains.popularlibraries.view.userdetails

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.popularlibraries.model.GitHubUserRepos

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun showUser(user: GitHubUserRepos)
    fun showLoading()
    fun hideLoading()
}