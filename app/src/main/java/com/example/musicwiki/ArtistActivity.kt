package com.example.musicwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.ArtistActivityViewModelfactory
import com.example.musicwiki.ViewModelFactories.ArtistViewModelFactory
import com.example.musicwiki.ViewModels.ArtistActivityViewModel
import com.example.musicwiki.adapters.ArtistAlbumAdapter
import com.example.musicwiki.adapters.ArtistTagsAdapter
import com.example.musicwiki.adapters.ArtistTracksAdapter
import com.example.musicwiki.data.artistData.Artist
import com.example.musicwiki.util.MyUtils
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class ArtistActivity : AppCompatActivity() {
    private lateinit var artistActivityViewModel: ArtistActivityViewModel
    private lateinit var artistfactory : ArtistActivityViewModelfactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        val artist = intent.getStringExtra("artist")
        val api = DetailsApi()
        val repository = InfoRepository(api,this)
        artistfactory = ArtistActivityViewModelfactory(repository,artist!!)
        artistActivityViewModel = ViewModelProviders.of(this,artistfactory).get(ArtistActivityViewModel::class.java)
        artistActivityViewModel.Artist.observe(this){artist->
            spinKitViewArtist.visibility = View.GONE
            labelPlayCount.visibility = View.VISIBLE
            labellisteners.visibility = View.VISIBLE
            topAlbumsTextView.visibility = View.VISIBLE
            topTracksTextView.visibility = View.VISIBLE
            playCount.visibility = View.VISIBLE
            listeners.visibility = View.VISIBLE
            Log.d("Artist", "onCreate: ${artist}")
            Glide.with(this)
                .load(artist.artist.image[3].text)
                .placeholder(R.drawable.progress_animation)
                .into(artistImage)
            playCount.text = artist.artist.stats.playcount
            listeners.text = artist.artist.stats.listeners
            recyclerViewArtistTags.also {
                it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter = ArtistTagsAdapter(artist.artist.tags.tag)
            }
            var str : String = artist.artist.bio.summary
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
            infoTextViewArtist.text = str

        }
        artistActivityViewModel.ArtistTopAlbums.observe(this){albums->
            Log.d("ArtistAlbums", "onCreate: ${albums.topalbums}")
            artistTopAlbums.also {
                it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter = ArtistAlbumAdapter(albums.topalbums.album,this)
            }
        }
        artistActivityViewModel.ArtistTopTracks.observe(this){tracks->
            Log.d("ArtistTracks", "onCreate: ${tracks.toptracks}")
            artistTopTracks.also {
                it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                it.setHasFixedSize(true)
                it.adapter = ArtistTracksAdapter(tracks.toptracks.track,this)
            }
        }

    }

}