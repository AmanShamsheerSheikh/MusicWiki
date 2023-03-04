package com.example.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.albumData.Tag
import com.example.musicwiki.databinding.AlbumtagitemBinding
import com.example.musicwiki.databinding.TagitemBinding

class AlbumActivityAdapter(private val list: List<Tag>) : RecyclerView.Adapter<AlbumActivityAdapter.AlbumActivityViewHolder>() {

    inner class AlbumActivityViewHolder (
        val albumtagitemBinding : AlbumtagitemBinding
    ): RecyclerView.ViewHolder(albumtagitemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumActivityViewHolder =
        AlbumActivityViewHolder(
            DataBindingUtil.inflate<AlbumtagitemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.albumtagitem,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AlbumActivityViewHolder, position: Int) {
        holder.albumtagitemBinding.tagText.text = list[position].name

    }

    override fun getItemCount(): Int {
        return list.size
    }
}