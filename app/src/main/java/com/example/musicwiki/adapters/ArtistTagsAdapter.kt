package com.example.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.artistData.Tag
import com.example.musicwiki.databinding.AlbumtagitemBinding
import com.example.musicwiki.databinding.TagitemBinding

class ArtistTagsAdapter(private val list: List<Tag>) : RecyclerView.Adapter<ArtistTagsAdapter.ArtistTagsViewHolder>() {
    inner class  ArtistTagsViewHolder(
        val albumtagitemBinding: AlbumtagitemBinding
    ): RecyclerView.ViewHolder(albumtagitemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArtistTagsViewHolder(
            DataBindingUtil.inflate<AlbumtagitemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.albumtagitem,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArtistTagsViewHolder, position: Int) {
        holder.albumtagitemBinding.tagText.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}