package com.dyshuk.android.repositorypatterncaching.database.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dyshuk.android.repositorypatterncaching.domain.Video

@Entity
data class DatabaseVideo constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}
