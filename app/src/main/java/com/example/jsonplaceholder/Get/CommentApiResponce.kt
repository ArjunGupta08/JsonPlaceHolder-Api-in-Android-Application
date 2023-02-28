package com.example.jsonplaceholder.Get

data class CommentApiResponce(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)