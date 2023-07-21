package com.andremw96.core.data.remote.network

import com.andremw96.core.data.remote.response.GenreListResponse
import retrofit2.http.GET

interface MovieDbApi {
    @GET("genre/movie/list")
    suspend fun getGenreList(): GenreListResponse

}
