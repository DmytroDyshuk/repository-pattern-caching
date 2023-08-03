package com.dyshuk.android.repositorypatterncaching.ui.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dyshuk.android.repositorypatterncaching.R
import com.dyshuk.android.repositorypatterncaching.databinding.VideoItemBinding
import com.dyshuk.android.repositorypatterncaching.domain.Video

class VideosAdapter(val callback: VideoClick) : RecyclerView.Adapter<VideosViewHolder>() {

    var videos: List<Video> = emptyList()
        set(value) {
            field = value


            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val withDataBinding: VideoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            VideosViewHolder.LAYOUT,
            parent,
            false)
        return VideosViewHolder(withDataBinding)
    }

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }
}

class VideosViewHolder(val viewDataBinding: VideoItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.video_item
    }
}

class VideoClick(val block: (Video) -> Unit) {
    fun onClick(video: Video) = block(video)
}