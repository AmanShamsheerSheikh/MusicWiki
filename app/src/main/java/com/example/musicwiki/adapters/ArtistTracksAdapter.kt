package com.example.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.data.albumData.Album
import com.example.musicwiki.data.albumData.Image
import com.example.musicwiki.data.albumData.Tag
import com.example.musicwiki.data.artistTopAlbums.ArtistAlbums
import com.example.musicwiki.data.artistTopTracks.Track
import com.example.musicwiki.databinding.AlbumtagitemBinding
import com.example.musicwiki.databinding.ImageItemBinding
import com.example.musicwiki.databinding.TagitemBinding
import com.example.musicwiki.databinding.TrackItemBinding

class ArtistTracksAdapter(private val list: List<Track>,private val context: Context) : RecyclerView.Adapter<ArtistTracksAdapter.ArtistTrackViewHolder>() {

    inner class ArtistTrackViewHolder (
        val trackItemBinding: TrackItemBinding
    ): RecyclerView.ViewHolder(trackItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistTrackViewHolder =
        ArtistTrackViewHolder(
            DataBindingUtil.inflate<TrackItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.track_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArtistTrackViewHolder, position: Int) {
        holder.trackItemBinding.artistName.text = list[position].artist.name
        holder.trackItemBinding.trackName.text = list[position].name

    }

    override fun getItemCount(): Int {
        return list.size
    }
}