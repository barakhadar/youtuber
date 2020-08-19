package com.hookiz.youtuber.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookiz.youtuber.R
import com.hookiz.youtuber.objects.App
import com.hookiz.youtuber.objects.Playlist
import com.hookiz.youtuber.ui.adapters.PlaylistsAdapter
import com.hookiz.youtuber.ui.viewmodel.PlaylistsActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class PlaylistsActivity : AppCompatActivity() {

    private val TAG = "PlaylistsActivity"
    lateinit var viewModel : PlaylistsActivityViewModel

    override fun onCreate( savedInstanceState : Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )
        initViewModel()
    }

    private fun initViewModel() {
        Log.d( TAG, "initViewModel()" )
        viewModel = ViewModelProvider( this ).get( PlaylistsActivityViewModel::class.java )
        ( application as App ).component.inject( viewModel )

        val playlistsObserver = Observer<List<Playlist>> { data ->
            Log.d( TAG, "initViewModel --> Data was updated --> ${data.size}" )
            val adapter = PlaylistsAdapter()
            adapter.setPlaylists( data )
            listsRecycler.adapter = adapter
            listsRecycler.layoutManager = LinearLayoutManager(this )
        }
        viewModel.playlists.observe(this, playlistsObserver )
        viewModel.initLiveData()
    }
}
