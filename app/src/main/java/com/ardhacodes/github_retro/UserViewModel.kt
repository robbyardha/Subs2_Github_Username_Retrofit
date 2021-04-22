package com.ardhacodes.github_retro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val listuser = MutableLiveData<ArrayList<Githubuser>>()

    //set Search
    fun setSearchUsername(query: String){
         BaseUrlClient.baseUrlApi
            .getSearchUsers(query)
            .enqueue(object : Callback<GithubResponse>{
                override fun onResponse(
                        call: Call<GithubResponse>,
                        response: Response<GithubResponse>
                ) {
                    if (response.isSuccessful){
                        listuser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    Log.d("failure", t.message.toString())
                }

            })
    }

    //get Search User
    fun getSearchUsers() : LiveData<ArrayList<Githubuser>>{
        return listuser
    }

    //set User
    fun setUsers()
    {
        BaseUrlClient.baseUrlApi
                .getUsers()
                .enqueue(object : Callback<GithubResponse>{
                    override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                        if (response == null){
                            listuser.postValue(response.body()?.items)
                        }
                    }

                    override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                        Log.d("failure", t.message.toString())
                    }

                })
    }

    fun getUsers() : LiveData<ArrayList<Githubuser>>
    {
        return listuser
    }

}