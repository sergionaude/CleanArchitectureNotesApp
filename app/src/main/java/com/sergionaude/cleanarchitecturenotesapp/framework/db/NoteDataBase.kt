package com.sergionaude.cleanarchitecturenotesapp.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "note.db"

        private var instance: NoteDataBase? = null

        private fun create(context: Context): NoteDataBase =
            Room
                .databaseBuilder(context, NoteDataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): NoteDataBase =
            (
                instance ?: create(context = context).also {
                    instance = it
                }
            )
    }
}
