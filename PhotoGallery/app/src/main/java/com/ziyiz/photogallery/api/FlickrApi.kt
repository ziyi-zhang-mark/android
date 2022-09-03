package com.ziyiz.photogallery.api

import retrofit2.http.GET

// TODO: remove before commit
private const val API_KEY = "558f5e49707e990ca2b3e2ae1c8164a7"

interface FlickrApi {
    @GET("services/rest/?method=flickr.interestingness.getList" +
        "&api_key=$API_KEY" +
        "&format=json" +
        "&nojsoncallback=1" +
        "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}