package com.amroz.testystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

class MainDashbourd : AppCompatActivity() {
    lateinit var nu:TextView
    private lateinit var dashViewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashbourd)
        dashViewModel =
            ViewModelProviders.of(this).get(DashViewModel::class.java)



        var user = Feachers()
        val LiveData = user.fetchContents()
        LiveData.observe(this, Observer {

            //nu.text=it.size.toString()
        })







    }
}