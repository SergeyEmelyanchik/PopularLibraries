package ru.geekbrains.popularlibraries

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun setCounterOneText(counter: String)

    fun setCounterTwoText(counter: String)

    fun setCounterThirdText(counter: String)
}