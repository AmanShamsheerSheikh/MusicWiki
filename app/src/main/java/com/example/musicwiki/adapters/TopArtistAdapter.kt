package com.example.musicwiki.adapters

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.AlbumActivity
import com.example.musicwiki.ArtistActivity
import com.example.musicwiki.R
import com.example.musicwiki.data.topArtistsData.Artist
import com.example.musicwiki.data.topArtistsData.TopArtists
import com.example.musicwiki.databinding.ArtistItemBinding
import com.example.musicwiki.databinding.ImageItemBinding

class TopArtistAdapter(private val list: List<Artist>, private val context: Context): RecyclerView.Adapter<TopArtistAdapter.topArtistsRecyclerViewViewHolder>() {

    inner class  topArtistsRecyclerViewViewHolder(
        val artistItemBinding: ArtistItemBinding
    ): RecyclerView.ViewHolder(artistItemBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): topArtistsRecyclerViewViewHolder =
        topArtistsRecyclerViewViewHolder(
            DataBindingUtil.inflate<ArtistItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.artist_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: topArtistsRecyclerViewViewHolder, position: Int) {
        holder.artistItemBinding.artistName.text = list[position].name
        holder.artistItemBinding.artistRank.text = list[position].attr.rank
        holder.artistItemBinding.root.setOnClickListener{
            val intent = Intent(context, ArtistActivity::class.java)
            intent.putExtra("artist",list[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}