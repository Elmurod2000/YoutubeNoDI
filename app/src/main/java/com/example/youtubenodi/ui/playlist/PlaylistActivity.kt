package com.example.youtubenodi.ui.playlist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubenodi.core.common.Status
import com.example.youtubenodi.core.extentions.showToast
import com.example.youtubenodi.core.ui.BaseActivity
import com.example.youtubenodi.databinding.ActivityPlaylistBinding
import com.example.youtubenodi.remote.model.PlayListItem
import com.example.youtubenodi.ui.detailsplaylist.DetailsPlaylist
import com.example.youtubenodi.utils.NetworkStatus
import com.example.youtubenodi.utils.NetworkStatusHelper

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {
    override val viewModel: PlaylistViewModel by lazy { ViewModelProvider(this)[PlaylistViewModel::class.java] }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecycler(playList: List<PlayListItem>) {
        binding.recyclerPlayList.layoutManager = LinearLayoutManager(this@PlaylistActivity)
        binding.recyclerPlayList.adapter = PlaylistAdapter(playList, this::initClick)
    }

    private fun initClick(id: PlayListItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(this, DetailsPlaylist::class.java).apply {
                putExtra(PLAYLIST, id.id)
                startActivity(this)
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlayList().observe(this) {
            when (it.status) {
                Status.ERROR -> {
                    it.msg?.let { it1 -> showToast(it1) }
                    viewModel.loading.postValue(false)
                }
                Status.SUCCESS -> {
                    if (it.data != null) {
                        viewModel.loading.postValue(false)
                        setupRecycler(it.data.items)
                    }
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        checkInternetMethod()
    }

    private fun checkInternetMethod() {
        NetworkStatusHelper(this@PlaylistActivity).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.connection.parentConnection.isVisible = false
            } else {
                binding.connection.parentConnection.isVisible = true
                binding.connection.button.setOnClickListener {
                    if (NetworkStatus.Available == NetworkStatus.Available) {
                        binding.connection.parentConnection.isVisible = false
                    }
                }
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }

    companion object {
        const val PLAYLIST = "playlist"
    }
}