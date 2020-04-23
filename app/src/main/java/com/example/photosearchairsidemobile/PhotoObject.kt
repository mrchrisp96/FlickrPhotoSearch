package com.example.photosearchairsidemobile

// Data class containing information about the photos
// Represents the View Model(?)

data class Photo (
    val photoID: String,
    val photoURL: String,
    val photoTitle: String
)

data class PhotoSearchResponse (
    val photos: PhotoMetaData
)

data class PhotoMetaData (
    val page: Int,
    val photo: List<PhotoData>
)


data class PhotoData (
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)
