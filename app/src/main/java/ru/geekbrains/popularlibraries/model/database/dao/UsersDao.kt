package ru.geekbrains.popularlibraries.model.database.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibraries.model.database.UserWithReposDBObject
import ru.geekbrains.popularlibraries.model.database.entity.UserRepoDBEntity
import ru.geekbrains.popularlibraries.model.database.entity.UsersDBEntity

@Dao
abstract class UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(usersDbEntity: List<UsersDBEntity>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UsersDBEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(userRepoDbEntity: List<UserRepoDBEntity>): Completable


    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<UserRepoDBEntity>>

    @Transaction
    @Query("Select * from users where login =:login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>

}