package com.example.youtubenodi.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubenodi.R
import com.example.youtubenodi.core.extentions.load
import com.example.youtubenodi.databinding.ItemPlayListBinding
import com.example.youtubenodi.remote.model.PlayListItem
import com.example.youtubenodi.remote.model.PlaylistModel

class PlaylistAdapter(
    private val playList: List<PlayListItem>,
    private val initClick: (id: PlayListItem) -> Unit
) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistHolderOne>() {

    inner class PlaylistHolderOne(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPlayListBinding.bind(item)

        fun bind(playList: PlayListItem) = with(binding) {
            tvTitle.text = playList.snippet.title
            val videoCount = playList.contentDetail.itemCount.toString() + " видео"
            tvUnderTitle.text = videoCount
            imagePlaylist.load(playList.snippet.thumbnails.high.url)
            itemView.setOnClickListener {
                initClick(playList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolderOne {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_play_list, parent, false)
        return PlaylistHolderOne(view)
    }

    override fun onBindViewHolder(holderOne: PlaylistHolderOne, position: Int) {
        holderOne.bind(playList[position])
    }

    override fun getItemCount() = playList.size
}