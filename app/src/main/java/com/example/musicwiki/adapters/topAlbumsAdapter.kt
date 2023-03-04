package com.example.musicwiki.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.AlbumActivity
import com.example.musicwiki.DetailActivity
import com.example.musicwiki.R
import com.example.musicwiki.RecyclerViewOnClick
import com.example.musicwiki.data.topAlbumData.Album
import com.example.musicwiki.databinding.ImageItemBinding
import com.example.musicwiki.databinding.TagitemBinding

class topAlbumsAdapter(private val list: List<Album>,private val context: Context) : RecyclerView.Adapter<topAlbumsAdapter.topAlbumsRecyclerViewViewHolder>() {

    inner class  topAlbumsRecyclerViewViewHolder(
        val imageItemBinding: ImageItemBinding
    ): RecyclerView.ViewHolder(imageItemBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): topAlbumsRecyclerViewViewHolder =
        topAlbumsRecyclerViewViewHolder(
            DataBindingUtil.inflate<ImageItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.image_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: topAlbumsRecyclerViewViewHolder, position: Int) {
        val imageUrl = list[position].image[2].text
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.progress_animation)
            .into(holder.imageItemBinding.imageView)

//        holder.imageItemBinding.albumName.text = list[position].name

        holder.imageItemBinding.root.setOnClickListener{
            val intent = Intent(context, AlbumActivity::class.java)
            intent.putExtra("artist",list[position].artist.name)
            intent.putExtra("album",list[position].name)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}