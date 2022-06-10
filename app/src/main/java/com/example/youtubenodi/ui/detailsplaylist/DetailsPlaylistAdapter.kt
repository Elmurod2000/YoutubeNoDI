package com.example.youtubenodi.ui.detailsplaylist

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubenodi.R
import com.example.youtubenodi.core.extentions.load
import com.example.youtubenodi.databinding.ItemDetailPlaylistBinding
import com.example.youtubenodi.remote.model.PlayListItem
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)

class DetailsPlaylistAdapter(private val playList: List<PlayListItem>) :
    RecyclerView.Adapter<DetailsPlaylistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_playlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList[position])
    }

    override fun getItemCount() = playList.size

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemDetailPlaylistBinding.bind(item)
        fun onBind(playList: PlayListItem) = with(binding) {
            tvDetailItemTitle.text = playList.snippet.title
            ivItemVideo.load(playList.snippet.thumbnails.default.url)
            val date = playList.snippet.publishedAt
            val offsetDateTime = OffsetDateTime.parse(date)
            val localOffsetDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.ofHours(-2))
            tvDuration.text =
                String.format(localOffsetDateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu ")))
        }
    }
}