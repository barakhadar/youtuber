package com.hookiz.youtuber.server

import com.hookiz.youtuber.server.response.PlaylistListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    /** https://landing.cal-online.co.il/youtube/playlists.json **/
    @GET("youtube/playlists.json")
    fun fetchAllPlaylists() : Call<PlaylistListResponse?>?

}