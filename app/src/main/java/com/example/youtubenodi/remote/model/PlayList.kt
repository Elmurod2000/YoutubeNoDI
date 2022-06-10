package com.example.youtubenodi.remote.model

import com.google.gson.annotations.SerializedName

data class PageInfoX(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class PlaylistModel(
    val pageToken: String,
    val pageInfo: PageInfoX,
    val items: List<PlayListItem>
)

data class PlayListItem(
    val id: String,
    val snippet: Snippet,
    @SerializedName("contentDetails")
    val contentDetail: ContentDetail
)

data class ContentDetail(
    val itemCount: Int
)

data class Snippet(
    val title: String,
    val description: String,
    val customUri: String,
    val publishedAt: String,
    val thumbnails: ThumbnailsY,
    val country: String,
    val playlistId: String
)

data class Default(
    var url: String,
    var width: Int,
    var height: Int
)

data class ThumbnailsY(
    var default: Default,
    var high: High
)

data class High(
    val url: String
)

