package ru.geekbrains.popularlibraries.utils

import ru.geekbrains.popularlibraries.database.RepoDBObject
import ru.geekbrains.popularlibraries.database.UserDbObject
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.data.ReposDto
import ru.geekbrains.popularlibraries.model.data.UsersDto

fun mapToEntity(dto: UsersDto): GitHubUser {
    return GitHubUser(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToDBObject(dto: UsersDto): UserDbObject {
    return UserDbObject(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToEntity(userDbObject: UserDbObject): GitHubUser {
    return GitHubUser(
        id = userDbObject.id,
        login = userDbObject.login,
        avatarUrl = userDbObject.avatarUrl,
        reposUrl = userDbObject.reposUrl
    )
}

fun mapRepos(repoDto: RepoDBObject): ReposDto {
    return ReposDto(
        id = repoDto.id,
        forksCount = repoDto.forks,
        name = repoDto.name,
        nodeId = repoDto.nodeId,
        createdAt = repoDto.createdAt,
        description = repoDto.description,
        language = repoDto.language,
        updatedAt = repoDto.updatedAt
    )
}

fun mapReposToObject(repoDto: ReposDto, mUserId: Int): RepoDBObject {
    return RepoDBObject(
        id = repoDto.id,
        forks = repoDto.forksCount,
        name = repoDto.name,
        userId = mUserId,
        language = repoDto.language,
        description = repoDto.description,
        createdAt = repoDto.createdAt,
        nodeId = repoDto.nodeId,
        updatedAt = repoDto.updatedAt
    )
}

