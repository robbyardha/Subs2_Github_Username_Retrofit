package com.ardhacodes.github_retro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposVM : ViewModel() {
    val listReposArr = MutableLiveData<ArrayList<GithubRepos>>()

    //setter
    fun setRepos(username: String)
    {
        BaseUrlClient.baseUrlApi
            .getRepos(username)
            .enqueue(object : Callback<ArrayList<GithubRepos>> {
                override fun onResponse(
                    call: Call<ArrayList<GithubRepos>>,
                    response: Response<ArrayList<GithubRepos>>
                ) {
                    if(response.isSuccessful){
                        listReposArr.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubRepos>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    //Getter
    fun getRepos() : LiveData<ArrayList<GithubRepos>>
    {
        return listReposArr
    }
}