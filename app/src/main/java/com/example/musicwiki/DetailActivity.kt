package com.example.musicwiki

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import java.util.regex.Matcher
import java.util.regex.Pattern

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
            var str = it.tag.wiki.content
            val list = ArrayList<String>()
            val re: Pattern = Pattern.compile(
                "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
                Pattern.MULTILINE or Pattern.COMMENTS
            )
            val reMatcher: Matcher = re.matcher(str)
            while (reMatcher.find()) {
                list.add(reMatcher.group())
            }
            str = list.slice(0..2).joinToString("")
            infoTextView.text = str
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


