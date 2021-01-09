package com.amroz.testystore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DashboardFragment : Fragment() {

    private lateinit var dashViewModel: ViewModel
    var count:Int=0

    private lateinit var RecyclerView: RecyclerView
     var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashViewModel =
            ViewModelProviders.of(this).get(DashViewModel::class.java)
        type=arguments?.getSerializable("type")as String
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type == "dash") {


            var user = Feachers()
            val LiveData = user.fetchContents()
            LiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = UserAdapter(it)

            })
        }
    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            var view = inflater.inflate(R.layout.fragment_dashboard, container, false)

            RecyclerView = view.findViewById(R.id.rec)
            RecyclerView.layoutManager = LinearLayoutManager(context)
            return view
        }

    companion object {
        fun newInstance(data: String): DashboardFragment {
            val args = Bundle().apply {
                putSerializable("type", data)
            }
            return DashboardFragment().apply {
                arguments = args
            }
        }
    }


    // News Holder
    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {


        val cattitle = view.findViewById(R.id.cattitle) as TextView
        val sub_cat_title = view.findViewById(R.id.sub_cat_title) as TextView
        val sub2_cat_title = view.findViewById(R.id.sub2_cat_title) as TextView



        fun bind(news: Dash) {

            cattitle.text = news.name
            sub_cat_title.text = news.email
            sub2_cat_title.text =count.toString()


        }


    }

    // NewsAdapter
    inner class UserAdapter(var news: List<Dash>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            var view: View



            view = layoutInflater.inflate(
                R.layout.dash_list,
                parent, false
            )

            return UsersHolder(view)

        }


        override fun getItemCount(): Int {

            count=news.size

            return news.size

        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val news = news[position]
            if (holder is UsersHolder)
                holder.bind(news)


        }
    }
    }





