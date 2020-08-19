package com.hookiz.youtuber.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookiz.youtuber.R
import com.hookiz.youtuber.objects.App
import com.hookiz.youtuber.objects.VideoItem
import com.hookiz.youtuber.ui.adapters.VideoItemsAdapter
import com.hookiz.youtuber.ui.viewmodel.VideoItemsActivityViewModel
import kotlinx.android.synthetic.main.activity_video_items.*

class VideoItemsActivity : AppCompatActivity() {

    private val TAG = "VideoItemsActivity"
    lateinit var viewModel : VideoItemsActivityViewModel
    lateinit var playlistId : String

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_video_items )
        Log.d( TAG, "initViewModel() playlistId --> ${intent.getStringExtra( "playlistId" )}" )
        playlistId = intent.getStringExtra( "playlistId" )
        initViewModel()
    }

    private fun initViewModel() {
        Log.d( TAG, "initViewModel()" )
        viewModel = ViewModelProvider( this ).get( VideoItemsActivityViewModel::class.java )
        ( application as App).component.inject( viewModel )

        val videoItemsObserver = Observer<List<VideoItem>> { data ->
            Log.d( TAG, "initViewModel --> Data was updated --> ${data.size}" )
            val adapter = VideoItemsAdapter()
            adapter.setVideoItems( data )
            videoItemsRecycler.adapter = adapter
            videoItemsRecycler.layoutManager = LinearLayoutManager(this )
        }
        viewModel.initLiveData( playlistId )
        viewModel.videoItems.observe( this, videoItemsObserver )

    }
}
