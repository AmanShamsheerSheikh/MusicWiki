package com.example.musicwiki.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwiki.Api.DetailsApi
import com.example.musicwiki.R
import com.example.musicwiki.Repositorys.InfoRepository
import com.example.musicwiki.ViewModelFactories.TrackViewModelFactory
import com.example.musicwiki.adapters.TrackAdapter
import kotlinx.android.synthetic.main.fragment_track.*

class TrackFragment : Fragment() {



    private lateinit var trackViewModel: TrackViewModel

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
        trackViewModel = ViewModelProviders.of(this,TrackViewModelFactory(repository,name)).get(TrackViewModel::class.java)
        trackViewModel.tracks.observe(viewLifecycleOwner){trackData->
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