package com.app.wallpaperapp.webapi

import com.app.wallpaperapp.model.Hit

data class Result (

    val total: Long,
    val totalHits: Long,
    val hits: MutableList<Hit>
)
