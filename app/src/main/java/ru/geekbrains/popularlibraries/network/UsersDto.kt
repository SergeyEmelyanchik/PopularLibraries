package ru.geekbrains.popularlibraries.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersDto(
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("login")
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String,
) : Parcelable