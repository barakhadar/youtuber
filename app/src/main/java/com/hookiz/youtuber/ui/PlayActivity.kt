package com.hookiz.youtuber.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hookiz.youtuber.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {

    lateinit var videoId : String

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_play )

        videoId = intent.getStringExtra( "videoId" )
        Log.d("PlayActivity", "videoId --> $videoId")
        initVideo()
    }

    private fun initVideo() {
        lifecycle.addObserver( youtube_player_view )
        youtube_player_view.addYouTubePlayerListener( object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadOrCueVideo( lifecycle, videoId, 0f )
            }
        } )
    }

    override fun onBackPressed() {
        if ( youtube_player_view.isFullScreen()) {
            youtube_player_view.exitFullScreen()
        } else {
            super.onBackPressed()
        }
    }

}
