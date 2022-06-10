package com.example.youtubenodi.ui.detailsplaylist

import androidx.lifecycle.LiveData
import com.example.youtubenodi.App
import com.example.youtubenodi.core.ui.BaseViewModel
import com.example.youtubenodi.core.common.Resource
import com.example.youtubenodi.remote.model.PlaylistModel
import com.example.youtubenodi.repository.Repository

class DetailsPlaylistViewModel : BaseViewModel() {
    fun getDetailPlayList(id: String): LiveData<Resource<PlaylistModel>> {
        return App().repository.detailPlaylist(id)
    }
}