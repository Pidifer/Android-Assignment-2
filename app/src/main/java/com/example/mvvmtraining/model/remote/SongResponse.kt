package com.example.mvvmtraining.model.remote

data class SongResponse(
    val resultCount: Int,
    val results: List<Song>
)

data class Song(
    val artistName: String,
    val collectionName: String,
    val trackPrice: Float,
    val artworkUrl100: String,
    val previewUrl: String
)