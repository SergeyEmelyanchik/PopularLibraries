package ru.geekbrains.popularlibraries.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.geekbrains.popularlibraries.model.data.ReposDto

@Parcelize
data class GitHubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
    var repos:List<ReposDto>?=null
) : Parcelable