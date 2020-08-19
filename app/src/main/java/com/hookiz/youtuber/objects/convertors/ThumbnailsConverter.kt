package com.hookiz.youtuber.objects.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hookiz.youtuber.objects.ResourceId
import com.hookiz.youtuber.objects.Thumbnails

class ThumbnailsConverter {

    @TypeConverter
    fun toThumbnails( json : String ) : Thumbnails {
        val type = object : TypeToken<Thumbnails>() {}.type
        return Gson().fromJson( json.trim(), type )

    }

    @TypeConverter
    fun toString( thumbnails: Thumbnails) : String {
        return Gson().toJson( thumbnails )
    }

}