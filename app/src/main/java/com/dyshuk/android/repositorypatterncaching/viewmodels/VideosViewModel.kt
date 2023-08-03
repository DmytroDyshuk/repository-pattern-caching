package com.dyshuk.android.repositorypatterncaching.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dyshuk.android.repositorypatterncaching.domain.Video
import com.dyshuk.android.repositorypatterncaching.network.VideosNetwork
import com.dyshuk.android.repositorypatterncaching.network.asDomainModel
import kotlinx.coroutines.launch
import java.io.IOException

class VideosViewModel(application: Application) : AndroidViewModel(application) {

    private val _playlist = MutableLiveData<List<Video>>()
    val playlist: LiveData<List<Video>>
        get() = _playlist

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            val playlist = VideosNetwork.videos.getPlaylist()
            _playlist.postValue(playlist.asDomainModel())

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideosViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideosViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}