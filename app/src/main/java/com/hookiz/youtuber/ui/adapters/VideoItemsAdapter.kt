package com.hookiz.youtuber.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookiz.youtuber.R
import com.hookiz.youtuber.objects.VideoItem
import com.hookiz.youtuber.objects.utils.ImageManager
import com.hookiz.youtuber.ui.PlayActivity
import com.hookiz.youtuber.ui.VideoItemsActivity
import kotlinx.android.synthetic.main.playlist_item.view.*
import kotlinx.android.synthetic.main.playlist_item.view.playlistName
import kotlinx.android.synthetic.main.video_item_item.view.*

class VideoItemsAdapter : RecyclerView.Adapter<VideoItemsAdapter.VideoItemViewHolder>() {
    private var videoItems : List<VideoItem> = emptyList()

    fun setVideoItems( items: List<VideoItem> ) {
        this.videoItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ) : VideoItemViewHolder {
        return VideoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_item_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = videoItems.size

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int ) {
        holder.bindHolder( videoItems[position] )
    }

    class VideoItemViewHolder(itemView: View ) : RecyclerView.ViewHolder( itemView ) {

        fun bindHolder( videoItem : VideoItem ) {
            var url = videoItem.thumbnails?.maxres?.url
            url = url?.replace( "https", "http" )
            ImageManager.setImage( url ,itemView.videoPreview )
            itemView.video_item_name.text = videoItem.title

            itemView.setOnClickListener {
                val intent = Intent( itemView.context, PlayActivity::class.java )
                intent.putExtra( "videoId", videoItem.resourceId?.videoId )
                itemView.context.startActivity( intent )
            }
        }

    }
}