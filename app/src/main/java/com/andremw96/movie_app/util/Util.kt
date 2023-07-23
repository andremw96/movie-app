package com.andremw96.movie_app.util

object Util {
    fun String.fullPosterPhotoPath(): String {
        return "https://image.tmdb.org/t/p/w500/$this"
    }

    fun String.youtubeThumbnailVideoPath(): String {
        return "https://img.youtube.com/vi/$this/hqdefault.jpg"
    }

    fun String.youtubeWatchVideoPath(): String {
        return "https://www.youtube.com/watch?v=$this"
    }
}
