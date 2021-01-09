package com.amroz.testystore


import retrofit2.Call
import retrofit2.http.GET

interface DashApi {

    @GET("StoreApi/api/users_api.php")
    fun fetchContents(): Call<DashResponse>


}