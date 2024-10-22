package com.sergionaude.cleanarchitecturenotesapp.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :idNote")
    fun getNote(idNote: Long): NoteEntity

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteEntity>

    @Delete
    fun removeNote(noteEntity: NoteEntity)
}
