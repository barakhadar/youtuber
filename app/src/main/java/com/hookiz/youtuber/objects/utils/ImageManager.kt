package com.hookiz.youtuber.objects.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

private const val TAG = "ImageManager"

class ImageManager {

    companion object {
        fun setImage(url: String?, imageView: ImageView?) {
            Log.d(TAG, "setImage() url --> $url")
            if ( url != null ) {
                Picasso.get()
                    .load(url)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageView, object : Callback {
                        override fun onSuccess() {}
                        override fun onError(e: Exception) {
                            Picasso.get()
                                .load(url)
//                        .placeholder(R.drawable.placeholder_image)
                                .into(imageView, object : Callback {
                                    override fun onSuccess() {}
                                    override fun onError(e: Exception) { //Try again online if cache failed
                                        Log.d(TAG, "onError() Cannot load image from the web --> error: $e")
                                    }
                                })
                        }
                    })
            } else {
                imageView?.visibility = View.GONE
            }
        }
    }
}


// https://i.ytimg.com/vi/jsur8561_1A/hqdefault.jpg
// https://i.ytimg.com/vi/jsur8561_1A/hqdefault.jpg