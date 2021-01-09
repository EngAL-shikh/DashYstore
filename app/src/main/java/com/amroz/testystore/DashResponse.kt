package com.amroz.testystore

import com.google.gson.annotations.SerializedName

data class DashResponse (

    @SerializedName("getAllRows")
    var user:List<Dash>
){

}