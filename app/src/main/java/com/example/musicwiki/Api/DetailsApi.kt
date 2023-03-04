package com.example.musicwiki.Api


import com.example.musicwiki.data.albumData.Album
import com.example.musicwiki.data.artistData.Artist
import com.example.musicwiki.data.artistTopAlbums.ArtistAlbums
import com.example.musicwiki.data.artistTopTracks.ArtistTracks
import com.example.musicwiki.data.tagData
import com.example.musicwiki.data.tagInfo.TagInfo
import com.example.musicwiki.data.topAlbumData.topAlbums
import com.example.musicwiki.data.topArtistsData.TopArtists
import com.example.musicwiki.data.trackData.TrackData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {
    @GET("?method=tag.getinfo&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getInfo( @Query("tag") tagName : String) : Response<TagInfo>

    @GET("?method=tag.gettopalbums&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getTopAlbums( @Query("tag") tagName : String) : Response<topAlbums>

    @GET("?method=tag.gettopartists&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getTopArtists( @Query("tag") tagName : String) : Response<TopArtists>

    @GET("?method=tag.gettoptracks&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getTopTracks( @Query("tag") tagName : String) : Response<TrackData>

    @GET("?method=album.getinfo&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getAlbum( @Query("artist") artist : String,@Query("album") album : String) : Response<Album>

    @GET("?method=artist.getinfo&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getArtist( @Query("artist") artistName : String) : Response<Artist>

    @GET("?method=artist.gettopalbums&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getArtistTopAlbums( @Query("artist") artistName : String) : Response<ArtistAlbums>

    @GET("?method=artist.gettoptracks&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getArtistTopTracks( @Query("artist") artistName : String) : Response<ArtistTracks>

    @GET("?method=chart.gettoptags&api_key=02f3be79e2d1e21003215f7bc799f7be&format=json")
    suspend fun getTags() : Response<tagData>









    companion object{
        operator fun invoke(
        ) : DetailsApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://ws.audioscrobbler.com/2.0/")
                .build()
                .create(DetailsApi::class.java)
        }
    }
}