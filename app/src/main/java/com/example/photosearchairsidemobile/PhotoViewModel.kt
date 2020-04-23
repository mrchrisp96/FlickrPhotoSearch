package com.example.photosearchairsidemobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    // ViewModel() used for fetching the data for the GridView

    private val mutablePhotosList = MutableLiveData<List<Photo>>()
    val liveData: LiveData<List<Photo>> = mutablePhotosList

    init {
        viewModelScope.launch {
            val searchResponse = FlickrAuthClient.flickrClient.fetchImages()
            System.out.println(searchResponse)
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    photoID = photo.id,
                    photoURL = "https://farm${photo.id}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    photoTitle = photo.title
                )
            }
            mutablePhotosList.postValue(photosList)
        }
    }


}