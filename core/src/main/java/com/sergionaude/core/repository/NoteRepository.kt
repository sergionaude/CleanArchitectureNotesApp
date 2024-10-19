package com.sergionaude.core.repository

import com.sergionaude.core.data.Note

class NoteRepository(
    private val noteDataSource: NoteDataSource,
) {
    suspend fun addNoteValue(noteValue: Note) = noteDataSource.addNote(note = noteValue)

    suspend fun getNoteValue(idNote: Long) = noteDataSource.getNote(id = idNote)

    suspend fun getAllNotes() = noteDataSource.getAll()

    suspend fun removeNote(noteValue: Note) = noteDataSource.remove(noteValue)
}
