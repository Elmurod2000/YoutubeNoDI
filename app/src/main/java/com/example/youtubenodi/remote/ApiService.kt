package com.example.youtubenodi.remote

import com.example.youtubenodi.remote.model.PlaylistModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int,
    ): Call<PlaylistModel>

    @GET("playlistItems")
    fun getDetailPLayList(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResult: Int,
        ): Call<PlaylistModel>
}