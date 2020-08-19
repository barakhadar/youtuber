package com.hookiz.youtuber.objects

class Snippet(
    val publishedAt : String,
    val title : String,
    val description : String,
    val channelId : String,
    val channelTitle : String,
    val playlistId : String,
    val position : Int,
    val resourceId : ResourceId,
    val thumbnails : Thumbnails
) {
}