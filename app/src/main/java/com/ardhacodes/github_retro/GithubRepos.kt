package com.ardhacodes.github_retro

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepos(
    val name : String ="",
    val full_name : String = "",
):Parcelable