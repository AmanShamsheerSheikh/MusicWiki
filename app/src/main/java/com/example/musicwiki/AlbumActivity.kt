package com.example.musicwiki

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.AlbumActivityViewModelFactory
import com.example.musicwiki.ViewModels.AlbumActivityViewModel
import com.example.musicwiki.adapters.AlbumActivityAdapter
import kotlinx.android.synthetic.main.activity_album.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class AlbumActivity : AppCompatActivity() {
    private lateinit var albumActivityViewModel: AlbumActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        val album = intent.getStringExtra("album")
        val artist = intent.getStringExtra("artist")
        val api = DetailsApi()
        val repository = InfoRepository(api,this)
        Log.d("Album", "onCreate: ${album}")
        Log.d("Album", "onCreate: ${artist}")


        albumActivityViewModel = ViewModelProviders.of(this,
                AlbumActivityViewModelFactory(repository, artist!!, album!!)
            ).get(AlbumActivityViewModel::class.java)
            albumActivityViewModel.Album.observe(this) { a ->
                recyclerViewAlbum.also {
                    it.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    it.setHasFixedSize(true)
                    it.adapter = AlbumActivityAdapter(a.album.tags.tag)
                }
                Glide.with(this)
                    .load(a.album.image[3].text)
                    .placeholder(R.drawable.progress_animation)
                    .into(albumImage)
                spinKitViewAlbum.visibility = View.GONE
                var str : String? = a.album.wiki?.content
                val list = ArrayList<String>()
                val re: Pattern = Pattern.compile(
                    "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
                    Pattern.MULTILINE or Pattern.COMMENTS
                )
                var i :Int = 0
                if (str.isNullOrEmpty()){
                    str = "DATA NOT FOUND"
                }
                val reMatcher: Matcher = re.matcher(str!!)
                while (reMatcher.find()) {
                    list.add(reMatcher.group())
                }
                if(list.size > 1){
                    i = 2
                }else{
                    i = 0
                }
                str = list.slice(0..i).joinToString("")


                infoTextViewAlbum.text = str
            }


    }


}