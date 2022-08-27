package ru.geekbrains.popularlibraries

import moxy.MvpPresenter
import ru.geekbrains.popularlibraries.repository.GitHubRepository
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ONE
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_TWO
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ZERO

class CountersPresenter(private val repository: GitHubRepository) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUsers())
    }
}