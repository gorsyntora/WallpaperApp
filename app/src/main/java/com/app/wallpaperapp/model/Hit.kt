package com.app.wallpaperapp.model

data class Hit(

    val id: String,
    val pageURL: String,
    val webformatURL: String,
    val imageURL: String,
    val largeImageURL:String,
    val previewURL: String,
    val views: Long? = null,
    val downloads: Long? = null,
    val favorites: Long? = null,
    val likes: Long? = null,
    val comments: Long? = null,
    val user_id: String? = null,
    val user: String? = null,
    val userImageURL: String? = null
) {
    override fun toString(): String {
        return "Hit(id='$id', pageURL='$pageURL', webformatURL='$webformatURL', imageURL='$imageURL', previewURL='$previewURL', views=$views)"
    }
}