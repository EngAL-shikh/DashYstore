package com.amroz.testystore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

 class DashViewModel:ViewModel() {

    val LiveData: LiveData<List<Dash>>

    init {
        LiveData = Feachers().fetchContents()


    }
}