package com.sergionaude.cleanarchitecturenotesapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sergionaude.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "create_date")
    val creationDate: Long,
    @ColumnInfo(name = "update_date")
    val updateDate: Long,
) {
    companion object {
        fun fromNoteToEntity(note: Note) =
            NoteEntity(
                id = note.id,
                title = note.title,
                content = note.content,
                creationDate = note.creationTime,
                updateDate = note.updatedTime,
            )
    }

    fun fromEntityToNote() =
        Note(
            id = id,
            title = title,
            content = content,
            creationTime = creationDate,
            updatedTime = updateDate,
        )
}
