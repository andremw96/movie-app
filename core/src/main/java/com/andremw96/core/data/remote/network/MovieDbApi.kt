package com.andremw96.core.data.remote.network

import com.andremw96.core.BuildConfig
import com.andremw96.core.data.remote.response.GenreListResponse
import com.andremw96.core.data.remote.response.MovieListByGenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("genre/movie/list")
    suspend fun getGenreList(
        @Query("api_key") appId: String = BuildConfig.MOVIE_DB_API_KEY,
    ): GenreListResponse

    @GET("discover/movie")
    suspend fun getMovieListByGenreId(
        @Query("api_key") appId: String = BuildConfig.MOVIE_DB_API_KEY,
        @Query("with_genres") withGenres: String,
        @Query("page") page: Int,
    ): MovieListByGenreResponse
}
