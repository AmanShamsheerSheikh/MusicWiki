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
import com.example.musicwiki.databinding.AlbumtagitemBinding
import com.example.musicwiki.databinding.ArtistTrackItemBinding
import com.example.musicwiki.databinding.ImageItemBinding
import com.example.musicwiki.databinding.TagitemBinding

class ArtistAlbumAdapter(private val list: List<com.example.musicwiki.data.artistTopAlbums.Album>,private val context: Context) : RecyclerView.Adapter<ArtistAlbumAdapter.ArtistAlbumViewHolder>() {

    inner class ArtistAlbumViewHolder (
        val artistTrackItemBinding: ArtistTrackItemBinding
    ): RecyclerView.ViewHolder(artistTrackItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumViewHolder =
        ArtistAlbumViewHolder(
            DataBindingUtil.inflate<ArtistTrackItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.artist_track_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArtistAlbumViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].image[2].text)
            .placeholder(R.drawable.progress_animation)
            .into(holder.artistTrackItemBinding.imageView)
        holder.artistTrackItemBinding.albumNameTrack.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}