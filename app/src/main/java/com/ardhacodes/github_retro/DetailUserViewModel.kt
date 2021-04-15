package com.ardhacodes.github_retro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    val user = MutableLiveData<DetailGithubResponse>()

    //setter
    fun setUserDetail(username: String) {
        BaseUrlClient.baseUrlApi
            .getUserDetail(username)
            .enqueue(object : Callback<DetailGithubResponse>{
                override fun onResponse(call: Call<DetailGithubResponse>, response: Response<DetailGithubResponse>) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailGithubResponse>, t: Throwable) {
                    t.message?.let { Log.d("FAILURE", it) }
                }

            })
    }

    //getter
    fun getUserDetail() : LiveData<DetailGithubResponse>
    {
        return user
    }
}