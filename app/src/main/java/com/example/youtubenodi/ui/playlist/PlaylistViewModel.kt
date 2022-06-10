package com.example.youtubenodi.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtubenodi.App
import com.example.youtubenodi.core.ui.BaseViewModel
import com.example.youtubenodi.core.common.Resource
import com.example.youtubenodi.remote.model.PlaylistModel

class PlaylistViewModel : BaseViewModel() {
    fun getPlayList(): LiveData<Resource<PlaylistModel>> {
        return App().repository.playlist()
    }
}