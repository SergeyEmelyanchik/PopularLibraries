package ru.geekbrains.popularlibraries.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.geekbrains.popularlibraries.network.ReposDto

@Parcelize
data class GitHubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
) : Parcelable


@Parcelize
data class GitHubUserRepos(
    val user: GitHubUser,
    val reposList: List<ReposDto>
) : Parcelable