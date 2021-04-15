package com.ardhacodes.github_retro

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Githubuser(
    val login : String = "",
    val id : Int,
    val avatar_url : String ="",
    val type : String ="",
):Parcelable
