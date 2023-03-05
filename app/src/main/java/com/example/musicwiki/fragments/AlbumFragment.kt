package com.example.musicwiki.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.R
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.ArtistViewModelFactory
import com.example.musicwiki.adapters.topAlbumsAdapter
import kotlinx.android.synthetic.main.fragment_album.*


class AlbumFragment : Fragment() {

    private lateinit var artistViewModel: ArtistViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        var name = ""
        if (bundle != null) {
            name = bundle.getString("tag")!!
        }

        Log.d("name", "onActivityCreated: $name")
        val api = DetailsApi()
        val repository = InfoRepository(api,requireContext())

        artistViewModel = ViewModelProviders.of(this, ArtistViewModelFactory(repository,name)).get(ArtistViewModel::class.java)
        artistViewModel.topAlbums.observe(viewLifecycleOwner){topAlbums->
            Log.d("tagData", "onCreateView: ${topAlbums.albums.album}")
            topAlbumRecyclerview.also {
                it.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
                it.setHasFixedSize(true)
                it.adapter = topAlbumsAdapter(topAlbums.albums.album,requireContext())
            }
        }
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


}