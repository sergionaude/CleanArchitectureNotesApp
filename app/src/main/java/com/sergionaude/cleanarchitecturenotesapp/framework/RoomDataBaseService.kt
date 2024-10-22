package com.sergionaude.cleanarchitecturenotesapp.framework

import android.content.Context
import com.sergionaude.cleanarchitecturenotesapp.framework.db.NoteDataBase
import com.sergionaude.cleanarchitecturenotesapp.framework.db.NoteEntity
import com.sergionaude.core.data.Note
import com.sergionaude.core.repository.NoteDataSource

class RoomDataBaseService(
    context: Context,
) : NoteDataSource {
    private val noteDao = NoteDataBase.getInstance(context = context).noteDao()

    override suspend fun addNote(note: Note) = noteDao.insertNote(noteEntity = NoteEntity.fromNoteToEntity(note))

    override suspend fun getNote(id: Long): Note? = noteDao.getNote(idNote = id)?.fromEntityToNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNotes().map { noteEntity -> noteEntity.fromEntityToNote() }

    override suspend fun remove(note: Note) = noteDao.removeNote(noteEntity = NoteEntity.fromNoteToEntity(note = note))
}
