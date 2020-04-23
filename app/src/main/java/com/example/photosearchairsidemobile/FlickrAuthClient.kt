package com.example.photosearchairsidemobile

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

const val flickrKey: String = "fcf4b40109af58fa6c515ceb19133581"
val flickrSecret: String = "f13f7740a84274d3"
const val FLICKR_URL = "https://api.flickr.com/services/rest/"
const val SEARCHQUERY = ""
const val CONNECTION_TIMEOUT_MS: Long = 10

object FlickrAuthClient {
    val flickrClient: ApiService by lazy {
        Retrofit.Builder().baseUrl(FLICKR_URL)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(ApiService::class.java)


    }
}

interface ApiService {
    // Either add the api key to a file that is not being tracked with your version control system,
    // or add a gradle script to add it as a string resource (per Google's recommendation)
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=cats&api_key=$flickrKey")
    suspend fun fetchImages(): PhotoSearchResponse
}
