package com.example.musicwiki

import android.view.View
import com.example.musicwiki.data.tag.Tag

interface RecyclerViewOnClick {

    fun onRecyclerViewClick(view: View,tag: Tag)
}