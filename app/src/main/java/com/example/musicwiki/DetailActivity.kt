package com.example.musicwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.DetailViewModelFactory
import com.example.musicwiki.ViewModels.DetailViewModel
import com.example.musicwiki.adapters.FragmentAdapter
import com.example.musicwiki.fragments.AlbumFragment
import com.example.musicwiki.fragments.ArtistFragment
import com.example.musicwiki.fragments.TrackFragment
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_album.*

class DetailActivity : AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("tag")
        heading.text = name?.capitalize()
        val api = DetailsApi()
        val repository = InfoRepository(api,this)
        detailViewModel = ViewModelProviders.of(this,DetailViewModelFactory(repository,name!!)).get(DetailViewModel::class.java)

        detailViewModel.tagsInfo.observe(this){

            spinKitViewDetail.visibility = View.GONE
            heading.visibility = View.VISIBLE
            tabLayout.visibility = View.VISIBLE
            var string = it.tag.wiki.summary.replace("<a href=\"http://www.last.fm/tag/$name\">Read more on Last.fm</a>.", "")
            string = string.replace("\n","")
            infoTextView.text = string
        }


        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(AlbumFragment(),"Albums")
        fragmentAdapter.addFragment(ArtistFragment(),"Artists")
        fragmentAdapter.addFragment(TrackFragment(),"Tracks")
        fragmentAdapter.setName(name)

        viewpager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewpager)

    }


}


