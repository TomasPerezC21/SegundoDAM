package com.dam.vmappweb


data class Post(var id: Int,
                var date: String,
                var link: String,
                var title: TitlePost,
                var content: ContentPost) {

}

class ContentPost (var rendered: String, var protected: Boolean)

class TitlePost (var rendered: String)