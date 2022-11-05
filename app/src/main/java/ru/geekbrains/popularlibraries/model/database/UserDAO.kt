package ru.geekbrains.popularlibraries.model.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(userDBObject: List<UserDBObject>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllRepos(repoDBObject: List<RepoDBObject>): Completable

    @Query("Select * from users")
    abstract fun queryForAllUsers(): Single<List<UserDBObject>>

    @Query("Select * from repos")
    abstract fun queryForAllRepos(): Single<List<RepoDBObject>>

    @Transaction
    @Query("Select * from users where login =:login")
    abstract fun getUsersWithRepos(login: String): Single<UserWithReposDBObject>

}