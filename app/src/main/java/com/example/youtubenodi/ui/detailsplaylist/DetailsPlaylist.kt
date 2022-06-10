package com.example.youtubenodi.ui.detailsplaylist

import android.annotation.SuppressLint
import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubenodi.R
import com.example.youtubenodi.core.common.Status
import com.example.youtubenodi.core.extentions.showToast
import com.example.youtubenodi.core.ui.BaseActivity
import com.example.youtubenodi.databinding.ActivityDetailsPlaylistBinding
import com.example.youtubenodi.remote.model.PlayListItem
import com.example.youtubenodi.remote.model.PlaylistModel
import com.example.youtubenodi.ui.playlist.PlaylistActivity.Companion.PLAYLIST


@RequiresApi(Build.VERSION_CODES.O)
class DetailsPlaylist : BaseActivity<DetailsPlaylistViewModel, ActivityDetailsPlaylistBinding>() {
    override val viewModel: DetailsPlaylistViewModel by lazy { ViewModelProvider(this)[DetailsPlaylistViewModel::class.java] }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityDetailsPlaylistBinding {
        return ActivityDetailsPlaylistBinding.inflate(inflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        viewModel.loading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.getDetailPlayList(intent.getStringExtra(PLAYLIST).toString()).observe(this) {
            when (it.status) {
                Status.ERROR -> {
                    it.msg?.let { it1 -> showToast(it1) }
                }
                Status.SUCCESS -> {
                    if (it.data != null) with(binding) {
                        tvDetailPlaylistTitle.text = it.data.items[0].snippet.title
                        tvDetailPlaylistSubtitle.text = it.data.items[0].snippet.description
                        tvDetailPlaylistSubtitle.movementMethod = ScrollingMovementMethod()
                        (it.data.items.size.toString() + getString(R.string.video)).also { it1 ->
                            binding.tvDetailSeries.text = it1
                        }
                        setupRecycler(it.data.items)
                    }
                }
                Status.LOADING -> {
                }
            }
        }

    }

    private fun setupRecycler(detailPlaylist: List<PlayListItem>) {
        binding.recyclerDetail.apply {
            layoutManager = LinearLayoutManager(this@DetailsPlaylist)
            binding.recyclerDetail.adapter = DetailsPlaylistAdapter(detailPlaylist)
        }
    }
}