package com.example.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.data.topAlbumData.Album
import com.example.musicwiki.data.trackData.Track
import com.example.musicwiki.databinding.ImageItemBinding
import com.example.musicwiki.databinding.TrackItemBinding

class TrackAdapter(private val list: List<Track>, private val context: Context) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    inner class  TrackViewHolder(
        val trackItemBinding: TrackItemBinding
    ): RecyclerView.ViewHolder(trackItemBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackViewHolder =
        TrackViewHolder(
            DataBindingUtil.inflate<TrackItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.track_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.trackItemBinding.trackName.text = list[position].name
        holder.trackItemBinding.artistName.text = list[position].artist.name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}