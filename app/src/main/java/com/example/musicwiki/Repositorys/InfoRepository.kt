package com.example.musicwiki.Repositorys

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.data.albumData.Album
import com.example.musicwiki.data.artistTopAlbums.ArtistAlbums
import com.example.musicwiki.data.artistTopTracks.ArtistTracks
import com.example.musicwiki.data.tagInfo.TagInfo
import com.example.musicwiki.data.topAlbumData.topAlbums
import com.example.musicwiki.data.topArtistsData.TopArtists
import com.example.musicwiki.data.trackData.TrackData
import com.example.musicwiki.util.MyUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InfoRepository(val api: DetailsApi,private val context: Context) {

    private val _tagInfo = MutableLiveData<TagInfo>()
    val tagInfo : LiveData<TagInfo>
        get() = _tagInfo

    private val _topAlbums = MutableLiveData<topAlbums>()
    val topAlbums : LiveData<topAlbums>
        get() = _topAlbums

    private val _topArtists = MutableLiveData<TopArtists>()
    val topArtists : LiveData<TopArtists>
        get() = _topArtists

    private val _tracks = MutableLiveData<TrackData>()
    val tracks : LiveData<TrackData>
        get() = _tracks

    private val _album = MutableLiveData<Album>()
    val album : LiveData<Album>
        get() = _album

    private val _artist = MutableLiveData<com.example.musicwiki.data.artistData.Artist>()
    val artist : LiveData<com.example.musicwiki.data.artistData.Artist>
        get() = _artist

    private val _artistTopAlbums = MutableLiveData<ArtistAlbums>()
    val artistTopAlbums : LiveData<ArtistAlbums>
        get() = _artistTopAlbums

    private val _artistTopTracks = MutableLiveData<ArtistTracks>()
    val artistTopTracks : LiveData<ArtistTracks>
        get() = _artistTopTracks

    suspend fun getInfo(name: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $name")
            GlobalScope.launch {
                val result = api.getInfo(name)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _tagInfo.postValue(result.body())
                }
            }

        }
    }
    suspend fun getTopAlbums(name: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $name")
            GlobalScope.launch {
                val result = api.getTopAlbums(name)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _topAlbums.postValue(result.body())
                }
            }

        }
    }

    suspend fun getTopArtists(name: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $name")
            GlobalScope.launch {
                val result = api.getTopArtists(name)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _topArtists.postValue(result.body())
                }
            }

        }
    }

    suspend fun getTopTracks(name: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $name")
            GlobalScope.launch {
                val result = api.getTopTracks(name)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _tracks.postValue(result.body())
                }
            }

        }
    }

    suspend fun getAlbum(artistName: String,albumName : String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $artistName")
            GlobalScope.launch {
                val result = api.getAlbum(artistName,albumName)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _album.postValue(result.body())
                }
            }

        }
    }

    suspend fun getArtist(artistName: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $artistName")
            GlobalScope.launch {
                val result = api.getArtist(artistName)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _artist.postValue(result.body())
                }
            }

        }
    }

    suspend fun getArtistTopAlbums(artistName: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $artistName")
            GlobalScope.launch {
                val result = api.getArtistTopAlbums(artistName)
                Log.d("res", "getInfo: $result")
                if(result.body() != null){
                    _artistTopAlbums.postValue(result.body())
                }
            }

        }
    }

    suspend fun getArtistTopTracks(artistName: String){
        if(MyUtils.isInternetAvailable(context)){
            Log.d("name", "getInfo: $artistName")
            GlobalScope.launch {
                val result = api.getArtistTopTracks(artistName)
                Log.d("TOPTRACKS", "getInfo: $result")
                if(result.body() != null){
                    _artistTopTracks.postValue(result.body())
                }
            }

        }
    }


}