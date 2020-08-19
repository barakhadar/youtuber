package com.hookiz.youtuber.objects.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hookiz.youtuber.objects.ResourceId

class ResourceIdConverter {

    @TypeConverter
    fun toResourceId( json : String ) : ResourceId {
        val type = object : TypeToken<ResourceId>() {}.type
        return Gson().fromJson( json.trim(), type )

    }
    @TypeConverter
    fun toString( resourceId: ResourceId ) : String {
        return Gson().toJson( resourceId )
    }

}