package ru.geekbrains.popularlibraries

import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ONE
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_TWO
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ZERO

class CountersPresenter(private val model: CountersModel) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun onCounterOneClick() {
        val newValue = model.next(DEFAULT_VALUE_ZERO)
        viewState.setCounterOneText(newValue.toString())
    }

    fun onCounterTwoClick() {
        val newValue = model.next(DEFAULT_VALUE_ONE)
        viewState.setCounterTwoText(newValue.toString())
    }

    fun onCounterThirdClick() {
        val newValue = model.next(DEFAULT_VALUE_TWO)
        viewState.setCounterThirdText(newValue.toString())
    }
}