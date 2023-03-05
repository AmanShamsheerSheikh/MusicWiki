package com.example.musicwiki.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.R
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.ArtistViewModelFactory
import com.example.musicwiki.adapters.TrackAdapter
import com.example.musicwiki.util.MyUtils
import kotlinx.android.synthetic.main.fragment_track.*


class TrackFragment : Fragment() {
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var artistViewModelFactory: ArtistViewModelFactory
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
        artistViewModelFactory = ArtistViewModelFactory(repository,name!!)
        artistViewModel = ViewModelProvider(requireActivity(), artistViewModelFactory).get(ArtistViewModel::class.java)
        artistViewModel.tracks.observe(viewLifecycleOwner){trackData->
            trackRecyclerview.also {
                it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                it.setHasFixedSize(true)
                it.adapter = TrackAdapter(trackData.tracks.track,requireContext())

            }
        }

        return inflater.inflate(R.layout.fragment_track, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }




}