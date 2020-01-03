package com.wisnu.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wisnu.movieapp.model.TVResponse
import com.wisnu.movieapp.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvViewModel : ViewModel() {
    private var data = MutableLiveData<TVResponse>()

    init {
        getMovie()
    }

    private fun getMovie() {
        NetworkConfig().create().getDiscoverTV().enqueue(object : Callback<TVResponse> {
            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                Log.e("Exception", t.message)
                data.value = null
            }

            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                if (response.isSuccessful) {
                    response.body().let {
                        data.value = it
                    }
                }
            }

        })
    }

    fun setData(): MutableLiveData<TVResponse> {
        return data
    }
}