package com.sergionaude.core.data

data class Note(
    var id: Long = 0,
    var title: String,
    var content: String,
    var creationTime: Long,
    var updatedTime: Long,
)
