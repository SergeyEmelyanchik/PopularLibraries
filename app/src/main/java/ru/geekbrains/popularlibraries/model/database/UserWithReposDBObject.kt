package ru.geekbrains.popularlibraries.model.database

import androidx.room.Embedded
import androidx.room.Relation
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

data class UserWithReposDBObject(
    @Embedded
    val usersDbEntity: UsersDBEntity,
    @Relation(
        parentColumn = UsersDBEntity.PRIMARY_KEY,
        entityColumn = UserRepoDBEntity.FOREIGN_USER_KEY
    )
    val repos: List<UserRepoDBEntity>,
)