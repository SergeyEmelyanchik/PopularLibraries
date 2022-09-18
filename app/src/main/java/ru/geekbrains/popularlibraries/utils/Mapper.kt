package ru.geekbrains.popularlibraries.utils

import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.network.UsersDto

fun mapToEntity(dto: UsersDto): GitHubUser {
    return GitHubUser(id = dto.id, login = dto.login, avatarUrl = dto.avatarUrl)
}