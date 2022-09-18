package ru.geekbrains.popularlibraries.view.userdetails

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserScreenView : MvpView {
    fun setUser(login: String?)
}