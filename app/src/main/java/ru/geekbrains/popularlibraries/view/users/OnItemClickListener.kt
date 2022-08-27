package ru.geekbrains.popularlibraries.view.users

import ru.geekbrains.popularlibraries.model.GitHubUser

interface OnItemClickListener {
    fun onItemClick(gitHubUser: GitHubUser)
}