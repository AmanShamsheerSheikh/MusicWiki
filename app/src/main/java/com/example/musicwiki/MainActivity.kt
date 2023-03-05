package com.example.musicwiki

import android.animation.LayoutTransition
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.Repositorys.tagRepository
import com.example.musicwiki.ViewModelFactories.MainViewModelFactory
import com.example.musicwiki.ViewModels.MainViewModel
import com.example.musicwiki.adapters.InnerRecyclerViewAdapter
import com.example.musicwiki.data.tag.Tag
import com.example.musicwiki.room.tagDatabase
import com.example.musicwiki.util.MyUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.math.exp


class MainActivity : AppCompatActivity(), RecyclerViewOnClick {
    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var list2 : List<Tag>
    private lateinit var list1 : List<Tag>
    private var on : Int = 0
    private var i : Int = 0
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api = DetailsApi()
        val database = tagDatabase(this)
        val repository = tagRepository(api,database,applicationContext)
        factory = MainViewModelFactory(repository)
        if(MyUtils.isInternetAvailable(this)){
            getData(on)
            i += 1
        }

        loop()


    }
    fun getData(state : Int){
        if(MyUtils.isInternetAvailable(this)){
            viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
            viewModel.tags.observe(this, Observer {list->
                val size = list.size/2
                val subLists = list.chunked(size)
                list1 = subLists[0]
                list2 = subLists[0]+subLists[1]
                list1 = list1.slice(0..14)
                if(state == 0){
                    setRecyclerView(list1)
                }else{
                    setRecyclerView(list2)
                }

            })
        }
    }


    fun expand() {
        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expanded_button.setOnClickListener{
            if(on == 0){
                TransitionManager.beginDelayedTransition(layout, AutoTransition())
                getData(1)
                on = 1
                Toast.makeText(this,"Scroll to see more genres",Toast.LENGTH_LONG).show()
                expanded_button.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
            }else{
                TransitionManager.beginDelayedTransition(layout, AutoTransition())
                getData(0)
                on= 0

                expanded_button.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            }
        }


    }

    private fun setRecyclerView(list: List<Tag>){
        insideCardRecyclerView.also {
            it.layoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
            it.setHasFixedSize(true)
            spinKitView.visibility = View.GONE
            it.adapter = InnerRecyclerViewAdapter(list,this)
        }
    }

    override fun onRecyclerViewClick(view: View, tag: Tag) {
        if(MyUtils.isInternetAvailable(this)){
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("tag",tag.name)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show()
        }

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialogInterface, i -> //
                    finishAffinity()
                }).setNegativeButton("No", null)
            .show()
    }



    private fun loop() {
        CoroutineScope(IO).launch {
            delay(1000)
            CoroutineScope(Main).launch {
                if(MyUtils.isInternetAvailable(this@MainActivity) && (i == 0)){
                    getData(0)
                    i += 1
                }
                expand()
                loop()
            }
        }
    }
}





