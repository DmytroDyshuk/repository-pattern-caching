package com.dyshuk.android.repositorypatterncaching.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dyshuk.android.repositorypatterncaching.database.VideosDatabase
import com.dyshuk.android.repositorypatterncaching.database.video.asDomainModel
import com.dyshuk.android.repositorypatterncaching.domain.Video
import com.dyshuk.android.repositorypatterncaching.network.VideosNetwork
import com.dyshuk.android.repositorypatterncaching.network.asDatabaseModel
import com.dyshuk.android.repositorypatterncaching.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> = database.videosDao.getVideos().map {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = VideosNetwork.videos.getPlaylist()
            database.videosDao.insertAll(playlist.asDatabaseModel())
        }
    }
}