package com.hookiz.youtuber.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookiz.youtuber.R
import com.hookiz.youtuber.objects.Playlist
import com.hookiz.youtuber.ui.VideoItemsActivity
import kotlinx.android.synthetic.main.playlist_item.view.*

class PlaylistsAdapter : RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {
    private val TAG = "PlaylistsAdapter"
    private var playlists : List<Playlist> = emptyList()

    fun setPlaylists( items: List<Playlist> ) {
        this.playlists = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ) : PlaylistViewHolder {
        return PlaylistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.playlist_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = playlists.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int ) {
        holder.bindHolder( playlists[position] )
    }

    class PlaylistViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView ) {

        private val TAG = "PlaylistViewHolder"

        fun bindHolder( playlist : Playlist ) {
            itemView.channelName.text = playlist.channelTitle
            itemView.playlistName.text = playlist.title

            itemView.setOnClickListener {
                val intent = Intent( itemView.context, VideoItemsActivity::class.java )
                intent.putExtra( "playlistId", playlist.id )
                itemView.context.startActivity( intent )
            }
        }

    }
}