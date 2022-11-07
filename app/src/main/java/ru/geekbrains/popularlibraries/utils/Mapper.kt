package ru.geekbrains.popularlibraries.utils

import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.network.ReposDto
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity
import ru.geekbrains.popularlibraries.model.network.UsersDto

fun mapToEntity(dto: UsersDto): GitHubUser {
    return GitHubUser(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToDBObject(dto: UsersDto): UsersDBEntity {
    return UsersDBEntity(
        id = dto.id,
        login = dto.login,
        avatarUrl = dto.avatarUrl,
        reposUrl = dto.reposUrl
    )
}

fun mapToEntity(usersDbEntity: UsersDBEntity): GitHubUser {
    return GitHubUser(
        id = usersDbEntity.id,
        login = usersDbEntity.login,
        avatarUrl = usersDbEntity.avatarUrl,
        reposUrl = usersDbEntity.reposUrl
    )
}

fun mapRepos(repoDto: UserRepoDBEntity): ReposDto {
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

fun mapReposToObject(repoDto: ReposDto, mUserId: Int): UserRepoDBEntity {
    return UserRepoDBEntity(
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

