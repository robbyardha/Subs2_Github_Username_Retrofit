package com.ardhacodes.github_retro

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseUrlClient {
    private const val BASE_URL = "https://api.github.com/"

    val retrofitBuild = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val baseUrlApi = retrofitBuild.create(ApiAuthorization::class.java)
}