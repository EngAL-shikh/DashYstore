package com.amroz.testystore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Feachers {

    var  dashApi:DashApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dashApi = retrofit.create(DashApi::class.java)
    }

    fun fetchContents(): LiveData<List<Dash>> {
        val responseLiveData: MutableLiveData<List<Dash>> = MutableLiveData()
        val newsRequest: Call<DashResponse> = dashApi.fetchContents()
        newsRequest.enqueue(object : Callback<DashResponse> {
            override fun onFailure(call: Call<DashResponse>, t: Throwable) {
                Log.e("TAG", "Failed to fetch News", t)
            }
            override fun onResponse(call: Call<DashResponse>, response: Response<DashResponse>
            ) {

                val Response:DashResponse? = response.body()
                val users:List<Dash> = Response?.user
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = users
                Log.d("onResponse", users.toString())
            }
        })
        return responseLiveData

    }
}