package com.dyshuk.android.repositorypatterncaching.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dyshuk.android.repositorypatterncaching.database.video.DatabaseVideo

@Dao
interface VideosDao {
    @Query("SELECT * FROM DatabaseVideo")
    fun getVideos(): LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<DatabaseVideo>)
}