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


class MainActivity : AppCompatActivity(), RecyclerViewOnClick {
    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var list2 : List<Tag>
    private lateinit var list1 : List<Tag>
    private var on : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api = DetailsApi()
        val database = tagDatabase(this)
        val repository = tagRepository(api,database,applicationContext)
        factory = MainViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)

        viewModel.tags.observe(this, Observer {list->
            val size = list.size/2
            val subLists = list.chunked(size)
            list1 = subLists[0]
            list2 = subLists[0]+subLists[1]
            list1 = list1.slice(0..14)
            setRecyclerView(list1)
        })



    }

    fun expand(view: View) {
        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        if(on == 0){
            TransitionManager.beginDelayedTransition(layout, AutoTransition())
            setRecyclerView(list2)
            on = 1
            Toast.makeText(this,"Scroll to see more genres",Toast.LENGTH_LONG).show()
            expanded_button.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
        }else{
            TransitionManager.beginDelayedTransition(layout, AutoTransition())
            setRecyclerView(list1)
            on= 0

            expanded_button.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
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

}





