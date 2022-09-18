package ru.geekbrains.popularlibraries.view.user

import ru.geekbrains.popularlibraries.model.GitHubUser

interface OnItemClickListener {
    fun onItemClick(gitHubUser: GitHubUser)
}