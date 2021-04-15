package com.ardhacodes.github_retro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingVM : ViewModel() {
    val listFollowingArr = MutableLiveData<ArrayList<Githubuser>>()

    //setter
    fun setListFollowing(username: String)
    {
        BaseUrlClient.baseUrlApi
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<Githubuser>> {
                override fun onResponse(
                    call: Call<ArrayList<Githubuser>>,
                    response: Response<ArrayList<Githubuser>>
                ) {
                    if(response.isSuccessful){
                        listFollowingArr.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<Githubuser>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    //Getter
    fun getListFollowing() : LiveData<ArrayList<Githubuser>>
    {
        return listFollowingArr
    }
}