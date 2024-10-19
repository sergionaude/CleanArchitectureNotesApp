package com.sergionaude.core.data

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val creationTime: Long,
    val updatedTime: Long,
)
