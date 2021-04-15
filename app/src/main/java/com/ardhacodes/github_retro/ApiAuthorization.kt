package com.ardhacodes.github_retro

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiAuthorization {
    @GET("users")
    @Headers("Authorization: token 0228bed0c26c5a5af364a09c733b1dfadf0e7482")
    fun getUsers():Call<GithubResponse>

    @GET("search/users")
    @Headers("Authorization: token 0228bed0c26c5a5af364a09c733b1dfadf0e7482")

    fun getSearchUsers(
        @Query("q") query: String

    ):Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 0228bed0c26c5a5af364a09c733b1dfadf0e7482")
    fun getUserDetail(
        @Path("username") username:String
    ):Call<DetailGithubResponse>


    @GET("users/{username}/followers")
    @Headers("Authorization: token 0228bed0c26c5a5af364a09c733b1dfadf0e7482")
    fun getFollowers(
        @Path("username") username:String
    ):Call<ArrayList<Githubuser>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 0228bed0c26c5a5af364a09c733b1dfadf0e7482")
    fun getFollowing(
        @Path("username") username:String
    ):Call<ArrayList<Githubuser>>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 0228bed0c26c5a5af364a09c733b1dfadf0e7482")
    fun getRepos(
        @Path("username") username:String
    ):Call<ArrayList<GithubRepos>>
}