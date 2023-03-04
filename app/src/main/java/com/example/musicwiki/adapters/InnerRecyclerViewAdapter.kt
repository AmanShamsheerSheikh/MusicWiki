package com.example.musicwiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.data.tag.Tag
import com.example.musicwiki.R
import com.example.musicwiki.RecyclerViewOnClick
import com.example.musicwiki.databinding.TagitemBinding


class InnerRecyclerViewAdapter(private val tags: List<Tag>, private val recyclerViewOnClick: RecyclerViewOnClick): RecyclerView.Adapter<InnerRecyclerViewAdapter.InnerRecyclerViewViewHolder>() {

    inner class  InnerRecyclerViewViewHolder(
        val tagitemBinding: TagitemBinding
    ): RecyclerView.ViewHolder(tagitemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        InnerRecyclerViewViewHolder(
            DataBindingUtil.inflate<TagitemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.tagitem,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: InnerRecyclerViewViewHolder, position: Int) {
        holder.tagitemBinding.tag = tags[position]
        holder.tagitemBinding.root.setOnClickListener{
            recyclerViewOnClick.onRecyclerViewClick(holder.tagitemBinding.root,tags[position])
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }


}