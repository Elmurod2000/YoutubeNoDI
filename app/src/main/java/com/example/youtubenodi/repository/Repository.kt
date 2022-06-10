package com.example.youtubenodi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubenodi.BuildConfig.API_KEY
import com.example.youtubenodi.`object`.Constant
import com.example.youtubenodi.core.common.Resource
import com.example.youtubenodi.remote.ApiService
import com.example.youtubenodi.remote.RetrofitClient
import com.example.youtubenodi.remote.model.PlaylistModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlist(): LiveData<Resource<PlaylistModel>> {
        val data = MutableLiveData<Resource<PlaylistModel>>()
        apiService.getPlaylists(
            Constant.part,
            Constant.channelId,
            API_KEY,
            Constant.maxResult
        ).enqueue(object : Callback<PlaylistModel> {
            override fun onResponse(call: Call<PlaylistModel>, response: Response<PlaylistModel>) {
                if (response.isSuccessful) {
                    if (response.body() != null)
                        data.value = Resource.success(response.body()!!)
                } else {
                    data.value = Resource.error(response.message(), null)
                }
            }

            override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                data.value = Resource.error(t.message, null)
            }
        })
        return data
    }


    fun detailPlaylist(id: String): LiveData<Resource<PlaylistModel>> {
        val data = MutableLiveData<Resource<PlaylistModel>>()
        apiService.getDetailPLayList(
            Constant.part,
            id,
            API_KEY,
            Constant.maxResult
        ).enqueue(object : Callback<PlaylistModel> {
            override fun onResponse(call: Call<PlaylistModel>, response: Response<PlaylistModel>) {
                if (response.isSuccessful)
                    if (response.body() != null)
                        data.value = Resource.success(response.body()!!)
            }
            override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                data.value = Resource.error(t.message, null)
            }
        })
        return data
    }
}