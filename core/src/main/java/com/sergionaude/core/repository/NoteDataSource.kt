package com.sergionaude.core.repository

import com.sergionaude.core.data.Note

interface NoteDataSource {
    suspend fun addNote(note: Note)

    suspend fun getNote(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note)
}
