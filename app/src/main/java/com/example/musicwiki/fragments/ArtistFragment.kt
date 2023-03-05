package com.example.musicwiki.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.R
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.ArtistViewModelFactory
import com.example.musicwiki.adapters.TopArtistAdapter
import kotlinx.android.synthetic.main.fragment_artist.*

class ArtistFragment : Fragment() {


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
        val api = DetailsApi()
        val repository = InfoRepository(api,requireContext())
        artistViewModel = ViewModelProviders.of(this,ArtistViewModelFactory(repository,name)).get(ArtistViewModel::class.java)
        artistViewModel.topArtists.observe(viewLifecycleOwner){topArtist->
            topArtistRecyclerview.also {
                it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                it.setHasFixedSize(true)
                it.adapter = TopArtistAdapter(topArtist.topartists.artist,requireContext())
            }

        }

        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}