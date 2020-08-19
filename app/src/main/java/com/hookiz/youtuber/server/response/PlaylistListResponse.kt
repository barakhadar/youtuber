package com.hookiz.youtuber.server.response

import com.hookiz.youtuber.objects.PageInfo
import com.hookiz.youtuber.objects.Playlist

class PlaylistListResponse(
    val pageInfo : PageInfo,
    val items : List<Playlist>
) {

}