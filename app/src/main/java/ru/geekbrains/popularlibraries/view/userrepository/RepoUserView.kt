package ru.geekbrains.popularlibraries.view.userrepository

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.popularlibraries.model.data.ReposDto

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoUserView : MvpView {
    fun showRepo(repo: ReposDto)
}