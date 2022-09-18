package ru.geekbrains.popularlibraries.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?
) : Parcelable