package com.andremw96.core.domain.schema

data class MovieReview(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Result(
        val author: String,
        val authorDetails: AuthorDetails,
        val content: String,
        val createdAt: String,
        val id: String,
        val updatedAt: String,
        val url: String
    ) {
        data class AuthorDetails(
            val avatarPath: String,
            val name: String,
            val rating: Int,
            val username: String
        )
    }
}
